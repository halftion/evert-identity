/* -----BEGIN RSA PRIVATE KEY-----
MIICXQIBAAKBgQDlOJu6TyygqxfWT7eLtGDwajtNFOb9I5XRb6khyfD1Yt3YiCgQ
WMNW649887VGJiGr/L5i2osbl8C9+WJTeucF+S76xFxdU6jE0NQ+Z+zEdhUTooNR
aY5nZiu5PgDB0ED/ZKBUSLKL7eibMxZtMlUDHjm4gwQco1KRMDSmXSMkDwIDAQAB
AoGAfY9LpnuWK5Bs50UVep5c93SJdUi82u7yMx4iHFMc/Z2hfenfYEzu+57fI4fv
xTQ//5DbzRR/XKb8ulNv6+CHyPF31xk7YOBfkGI8qjLoq06V+FyBfDSwL8KbLyeH
m7KUZnLNQbk8yGLzB3iYKkRHlmUanQGaNMIJziWOkN+N9dECQQD0ONYRNZeuM8zd
8XJTSdcIX4a3gy3GGCJxOzv16XHxD03GW6UNLmfPwenKu+cdrQeaqEixrCejXdAF
z/7+BSMpAkEA8EaSOeP5Xr3ZrbiKzi6TGMwHMvC7HdJxaBJbVRfApFrE0/mPwmP5
rN7QwjrMY+0+AbXcm8mRQyQ1+IGEembsdwJBAN6az8Rv7QnD/YBvi52POIlRSSIM
V7SwWvSK4WSMnGb1ZBbhgdg57DXaspcwHsFV7hByQ5BvMtIduHcT14ECfcECQATe
aTgjFnqE/lQ22Rk0eGaYO80cc643BXVGafNfd9fcvwBMnk0iGX0XRsOozVt5Azil
psLBYuApa66NcVHJpCECQQDTjI2AQhFc1yRnCU/YgDnSpJVm1nASoRUnU8Jfm3Oz
uku7JUXcVpt08DFSceCEX9unCuMcT72rAQlLpdZir876
-----END RSA PRIVATE KEY-----
-----BEGIN PUBLIC KEY-----
MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDlOJu6TyygqxfWT7eLtGDwajtN
FOb9I5XRb6khyfD1Yt3YiCgQWMNW649887VGJiGr/L5i2osbl8C9+WJTeucF+S76
xFxdU6jE0NQ+Z+zEdhUTooNRaY5nZiu5PgDB0ED/ZKBUSLKL7eibMxZtMlUDHjm4
gwQco1KRMDSmXSMkDwIDAQAB
-----END PUBLIC KEY-----

 */

var serverPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDSnHC1+DtujtXhZcak59mI6oyMWrgFOMK5Ur4/ZD2gEfC5AdvhxzV3MEKAhGCzBctgRIUkAsw2zUAcdW1e8noYfS5x0DrVNPu85BFGexDF0poeNaHvo6ThXe52GiY4jpG65J8oKgTIY3py4CMbLukOT5T9eHYW4+ZWeYZiAwdjrwIDAQAB";
var clientPrimeryKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANKccLX4O26O1eFlxqTn2YjqjIxauAU4wrlSvj9kPaAR8LkB2+HHNXcwQoCEYLMFy2BEhSQCzDbNQBx1bV7yehh9LnHQOtU0+7zkEUZ7EMXSmh41oe+jpOFd7nYaJjiOkbrknygqBMhjenLgIxsu6Q5PlP14dhbj5lZ5hmIDB2OvAgMBAAECgYEAivANIt5pRpwP2Kvtiagxx7MDmSvXrgot21gMjYExbkGnjkT9q0phqQk7NdEegbtW2WEafBEkaxqoKp4E0tgfVooRdZ9QXWefHQHsgANUbbMbbNbg5/pi3TaGmFMYoYC7xv6n6dZ+g9sdcPI469t4ujZJD2jSDg+dt4bLDVZ3bFECQQDolDtkR5lqIc26i9a0bwFYu/QlcNNNpbu1SsXwCNYLgiRNnmXNpNPoVj+R2CZn03T0E1brC1b1uhhfwqNYWNvTAkEA59Hi2y/mCmtADWj0qXYM9ICe1zUDfpZ/PUCh1j2Fmup/wUByB9Eq40bmDxoR2Uhqz2/ypDF/agkBGMQ0tD77NQJAPv6/Y3b9N8D2LzvnKLIUZEtsYqO/p/1m7UDWgiBykWVrndZ0bpHhj8GksEUiiea9e8qtNTa6FA6WwYE/qxNOXwJBAMZg+0Rc+fwCib6wCWcXKcD2bHd323Sxr1MogxXGchX/BLLIxoxf99akrm1P/uQ1L4oyn67Y2NNRv0mFZiC1IN0CQA7qgB4nqPdFjMJZROKnto7ya1wLRtvugX4167enQtgLOluyoBE5VOgfG/ZgiuMaoHBV0Pf2necraGFIWepQrEA=";
var sessionKey = "";
//提交用户名
function submits(){
    var phonenumber = $("#phoneNumber").val();
    var encrypt = new JSEncrypt();
    encrypt.setPublicKey(serverPublicKey);
    var decrypt = new JSEncrypt();
    decrypt.setPrivateKey(clientPrimeryKey);
    phonenumber = encrypt.encrypt(phonenumber);
    alert("服务端公钥："+ serverPublicKey +"\r\n"+"签名后内容：" + phonenumber);
    phonenumber = decrypt.decrypt(phonenumber);
    $("#phoneNumber").val(phonenumber);
    document.getElementById("form").submit();
}
//认证服务端
function verify(sessionkey,successInfo){
    // var decrypt = new JSEncrypt();
    // decrypt.setPrivateKey(clientPrimeryKey);
    // sessionKey = decrypt.decrypt(sessionkey);
    // var info = decrypt.decrypt(successInfo);
    $("#success").val("error");
    if(info == "server"){
        localStorage.setItem("sessionKey", sessionKey);
        $("#success").val("success");
    }
    document.getElementById("form").submit();
}

//aes加密
function encrypt(word){
    var key = CryptoJS.enc.Utf8.parse(sessionStorage.getItem("sessionKey"));
    var srcs = CryptoJS.enc.Utf8.parse(word);
    var encrypted = CryptoJS.AES.encrypt(srcs, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});
    return encrypted.toString();
}

// aes解密
function decrypt(word){
    var key = CryptoJS.enc.Utf8.parse(sessionStorage.getItem("sessionKey"));
    var decrypt = CryptoJS.AES.decrypt(word, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});
    return CryptoJS.enc.Utf8.stringify(decrypt).toString();
}