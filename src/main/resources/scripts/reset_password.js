var handler = new Object();

handler.reset = resetPassword;

function resetPassword(oldPassword,newPassword,confirmPassword) {
    if (oldPassword == "" || newPassword == "" || confirmPassword == "") {
        out.println('Please fill all the details!');
        return false;
    }
    else if (oldPassword == newPassword) {
        out.println("Old password and New Password cannot be same!");
        return false;
    }
    else if (newPassword != confirmPassword) {
        out.println("Password Mismatch!");
        return false;
    }
    return true;
}