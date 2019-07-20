package net.mbonnin.uploadbin

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class MainServlet : HttpServlet() {
    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
        System.out.println("service=${req.servletPath} pathInfo=${req.pathInfo}")
        
        if (req.method != "POST") {
            resp.sendError(500)
            return
        }

        req.inputStream.use {
            CloudStorage.bucket.create(req.pathInfo, it)
        }

        resp.status = 200
        resp.writer.write("ok")
    }
}
