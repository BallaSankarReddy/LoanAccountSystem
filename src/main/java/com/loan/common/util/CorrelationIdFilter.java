package com.loan.common.util;

import java.io.IOException;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class CorrelationIdFilter implements Filter
{
   private Random random = new Random();
   private final int MAX_ID_SIZE = 50;


   public void init(FilterConfig filterConfig) throws ServletException
   {
   }

   public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain filterChain)
      throws IOException, ServletException
   {
      String correlationId = ((HttpServletRequest) request).getHeader("X-Correlation-Id");
      correlationId = verifyOrCreateId(correlationId);
      MDC.put("correlation-id", correlationId);

      filterChain.doFilter(request, response);
   }

   public void destroy()
   {
   }

   public String verifyOrCreateId(String correlationId)
   {
      if(correlationId == null)
      {
         correlationId = generateCorrelationId();
      }
      //prevent on-purpose or accidental DOS attack that
      // fills logs with long correlation id provided by client
      else if (correlationId.length() > MAX_ID_SIZE)
      {
         correlationId = correlationId.substring(0, MAX_ID_SIZE);
      }

      return correlationId;

   }

   private String generateCorrelationId()
   {
      long randomNum = random.nextLong();
      return encodeBase62(randomNum);
   }

   /**
    * Encode the given Long in base 62
    * @param n  Number to encode
    * @return Long encoded as base 62
    */
   private String encodeBase62(long n)
   {

      final String base62Chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

      StringBuilder builder = new StringBuilder();

      //NOTE:  Appending builds a reverse encoded string.  The most significant value
      //is at the end of the string.  You could prepend(insert) but appending
      // is slightly better performance and order doesn't matter here.

      //perform the first selection using unsigned ops to get negative
      //numbers down into positive signed range.
      long index = Long.remainderUnsigned(n, 62);
      builder.append(base62Chars.charAt((int) index));
      n = Long.divideUnsigned(n, 62);
      //now the long is unsigned, can just do regular math ops
      while (n > 0)
      {
         builder.append(base62Chars.charAt((int) (n % 62)));
         n /= 62;
      }
      return builder.toString();
   }
}