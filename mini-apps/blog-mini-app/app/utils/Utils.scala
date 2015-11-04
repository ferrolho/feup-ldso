package utils

class Utils {

  def sha(s: String) = {
    val md = java.security.MessageDigest.getInstance("SHA-256")

    // add a salt. can replace salt with generated salt value
    val v = "salt" + s

    // return encoded value
    new sun.misc.BASE64Encoder().encode(md.digest(v.getBytes("UTF-8")))
  }
}
