var handler = new Object();

handler.reset = resetPassword;

function resetPassword(oldPassword,newPassword,confirmPassword) {
    if (oldPassword == "" || newPassword == "" || confirmPassword == "") {
        console.log('Please fill all the details');
        return false;
    }
    else if (oldPassword == newPassword) {
        console.log("Old password and New Password cannot be same");
        return false;
    }
    else if (newPassword != confirmPassword) {
        console.log("password mismatch");
        return false;
    }
    return true;
}