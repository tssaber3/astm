$(function () {
    //先查看是否有user 再将其发送给后台校验 查看他的权限 再展示不同的页面
    var username = localStorage.getItem("username");
    $.ajax(
        {
            url: 'http://localhost:8090/user/iflogin',
            type: 'POST',
            dataType: 'JSON',
            async: false,
            // xhrFields: {withCredentials: true},
            data: {
                username: username
            },
            success: function (data) {
                if(data == '')
                {
                    window.location.href = 'login.html';
                }
            }
        }
    );
    $(".btn-save").click(function () {
        if($("#new-password").val() != $("#new-password-again").val())
        {
            alert("两次密码不一致");
        }else if($("#new-password").val() == ''||$("#new-password-again").val() == '')
        {
            alert("请填写新密码");
        }
        else
        {
            var password =$("#new-password").val();
            $.ajax(
                {
                    url:'http://localhost:8090/user/changepassword',
                    type:'POST',
                    dataType:'text',
                    async: false,
                    data:{
                        username:username,
                        password:password
                    },
                    success:function (data) {
                        if(data != '')
                        {
                            alert("修改成功");
                            window.location.href = "info.html";
                        }
                    },
                    error:function (data) {
                    }
                }
            )
        }

    });
    $(".btn-back").click(function () {
        window.location.href = 'info.html';
    });
});
