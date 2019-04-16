$(function()
{
    //先查看是否有user 再将其发送给后台校验 查看他的权限 再展示不同的页面
    var username = localStorage.getItem("username");
    $.ajax(
        {
            url:'http://localhost:8090/user/iflogin',
            type:'POST',
            async:false,
            dataType:'JSON',
            // xhrFields: {withCredentials: true},
            data:{
                username:username
            },
            success:function (data) {
                if(data == 'null')
                {
                    window.location.href = 'login.html';
                }else {
                    var name = data.role.name;
                    if(name == '老师')
                    {
                        $(".tea-info").css("display","block");
                        $(".tea-info .info-item-nickname").text(data.nickname);
                        $(".tea-info .info-item-username").text(data.username);
                        $(".btn-shenghe").text("项目审核");
                        $(".btn-shenghe").attr("href","review.html");
                    }
                    else if(name == '学生')
                    {
                        $(".stu_info").css("display","block");
                        $(".stu_info .info-item-nickname").text(data.nickname);
                        $(".stu_info .info-item-username").text(data.username);
                        $(".stu_info .info-item-deparment").text(data.deparment);
                        $(".stu_info .info-item-score").text(data.score);
                        $(".stu_info .info-item-grade").text(data.grade+2016 + "级");
                    }
                }
            }
        }
    );
    $(".btn-out").click(function () {
        localStorage.clear();
        window.location.href = 'login.html';
    });
});