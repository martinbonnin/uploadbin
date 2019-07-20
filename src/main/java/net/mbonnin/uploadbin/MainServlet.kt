package net.mbonnin.uploadbin

import java.util.*
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class MainServlet : HttpServlet() {
    companion object {
        val REGEXP = Regex(".*Entity=([^#]*)#.*GOLD_REWARD_STATE.*")
    }
    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
        System.out.println("service=${req.servletPath} pathInfo=${req.pathInfo}")

        if (req.method != "POST") {
            resp.sendError(405)
            return
        }

        var name:String? = null
        val lines = req.inputStream.reader().readLines()
        for (line in lines) {
            val match = REGEXP.matchEntire(line)
            if (match != null && match.groupValues[1] != "Mbonnin") {
                name = match.groupValues[1]
                break
            }
        }
        if (name == null) {
            resp.sendError(422)
            return
        }

        val date = Date()
        val blobName = String.format("hsdata/${date.year + 1900}_%02d_%02d_$name", date.month + 1, date.date)
        CloudStorage.bucket.create(blobName, lines.joinToString("\n").byteInputStream())

        resp.status = 200
        resp.writer.write("ok")
    }
}
