$(function () {
   $(".btn-login").click(function () {
       var username = $("#username-input").val();
       var password = $("#password-input").val();
       // alert(username);
       $.ajax(
           {
               url:'http://localhost:8090/user/login',
               type:'POST',
               // xhrFields: {withCredentials: true},
               data:{
                   username:username,
                   password:password
               },
                success:function (data) {
                   //返回值是0：账密错误 1：管理员 2：老师 3：学生
                    //每次登录都对一次数据库 查看是否与相应页面的权限相同
                    if(data == 1)
                    {
                        //先清空 再加入   跳转页面
                        localStorage.clear();
                        localStorage.setItem("username",username);
                        window.location.href = 'admin.html';
                    }else if(data == 2)
                    {
                        localStorage.clear();
                        localStorage.setItem("username",username);
                        window.location.href = 'info.html';
                    }else if(data == 3)
                    {
                        localStorage.clear();
                        localStorage.setItem("username",username);
                        window.location.href = 'info.html';
                    }else if(data == 0)
                    {
                        //账密错误的提示
                        alert("账密错误");
                    }
                },
               error:function (data) {

               }
           }
       )
   }) 
});