import org.apache.commons.net.ftp.FTPClient
import java.io.IOException
import java.lang.Thread.sleep
import java.util.*


@Throws(IOException::class)
private fun ftpLst(pathParse: String): ArrayList<String> {
    val ftpClient = FTPClient()
    ftpClient.connect("ftp.zakupki.gov.ru", 21)
    ftpClient.login(userftp, passftp)
    ftpClient.enterLocalPassiveMode()
    ftpClient.changeWorkingDirectory(pathParse)
    val s = ArrayList(Arrays.asList(*ftpClient.listNames()))
    ftpClient.abort()
    return s
}

fun getListFtp(pathParse: String): ArrayList<String> {
    var arr = ArrayList<String>()
    var count = 1
    while (true) {
        try {

            arr = ftpLst(pathParse)
            break
        } catch (e: Exception) {
            if (count > 3) {
                println("Не смогли найти директорию после попытки $count, $pathParse, ${e.message}")
                break
            }
            count++
            try {
                sleep(5000)
            } catch (ignored: InterruptedException) {

            }

        }

    }

    return arr
}