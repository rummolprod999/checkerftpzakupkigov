import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class Sender(private val username: String, private val password: String) {
    private val props: Properties = Properties()

    init {

        props["mail.smtp.auth"] = "true"
        props["mail.smtp.starttls.enable"] = "true"
        props["mail.smtp.host"] = "mail.enter-it.ru"
        props["mail.smtp.port"] = "25"
    }

    fun send(subject: String, text: String, fromEmail: String, toEmail: String) {
        val session = Session.getInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(username, password)
            }
        })

        try {
            val message = MimeMessage(session)
            message.setFrom(InternetAddress(username))
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail))
            message.subject = subject
            message.setText(text)
            Transport.send(message)
        } catch (e: MessagingException) {
            throw RuntimeException(e)
        }

    }
}