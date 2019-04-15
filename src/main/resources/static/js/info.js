$(function()
{
    //先查看是否有user 再将其发送给后台校验 查看他的权限 再展示不同的页面
    var username = localStorage.getItem("username");
    $.ajax(
        {
            url:'http://localhost:8090/user/iflogin',
            type:'POST',
            xhrFields: {withCredentials: true},
            data:{
                username:username
            },
            success:function (data) {
                if(data == 'null')
                {

                }else if(data == 'relogin')
                {

                }else {
                    var obj = JSON.parse(data);
                    var role = obj.role;
                    alert(obj.toString());
                }
            }
        }
    )
});