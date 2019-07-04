package application.dto;

import javax.validation.constraints.NotBlank;

public class ResetPasswordRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String newPassword;

    @NotBlank
    private String oldPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
