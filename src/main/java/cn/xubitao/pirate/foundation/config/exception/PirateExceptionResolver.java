package cn.xubitao.pirate.foundation.config.exception;

import cn.xubitao.dolphin.foundation.exceptions.ClientException;
import cn.xubitao.dolphin.foundation.response.Response;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 15031046 on 2016/1/22.
 */
public class PirateExceptionResolver extends SimpleMappingExceptionResolver {
    public PirateExceptionResolver() {
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response, Object handler, Exception ex) {
        try {
            int status;
            if (ex instanceof NotFoundException) {
                status = 404;
            }
            if (ex instanceof ClientException) {
                status = 400;
            }

            status = ex instanceof ClientException ? 400 : 500;
            response.setStatus(status);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(Response.error(ex).toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
