$(function () {

    $("#register-btn").click(function () {
        if($("#nickname-register").val() == "" || $("#username-register").val() == ""  || $("#password-register").val() == ""  || $("#deparment-register").val() == ""  || $("#grade-register").val() == "" )
        {
            alert("请认真填写信息")
        }else {
            var nickname = $("#nickname-register").val();
            var username = $("#username-register").val();
            var password = $("#password-register").val();
            var deparment = $("#deparment-register").val();
            var grade = $("#grade-register").val();
            $.ajax(
                    {
                        url:'http://localhost:8090/user/addStudent',
                        type:'POST',
                        data:{
                            nickname:nickname,
                            username:username,
                            password:password,
                            deparment:deparment,
                            grade:grade
                        },
                        success:function (data) {
                            localStorage.clear();
                            localStorage.setItem("username",username);
                            window.location.href = 'info.html';
                        }
                    }
                )
        }
    })
   //  var finish1 = false;
   //  var finish2 = false;
   //  var finish3 = false;
   // $("#nickname-register").change(function () {
   //     var nickname = $("#nickname-register").val();
   //     var reg = /^([\u4E00-\u9FA5]|[\uE7C7-\uE7F3]|[a-zA-Z0-9_]){3,10}$/;//3到10位的中文或英文
   //     if(reg.test(nickname))
   //     {
   //         finish1 = true;
   //         $(".nickname-safe p").css("display","block");
   //         $(".nickname-safe div").css("display","block");
   //         $(".error-message.error-message-1").html("");
   //     }
   //     else
   //     {
   //         finish1 = false;
   //         $(".nickname-safe p").css("display","none");
   //         $(".nickname-safe div").css("display","none");
   //         $(".error-message.error-message-1").html("昵称为3-10位的中英字符");
   //     }
   // });
   // $("#password-register").change(function () {
   //     var password = $("#password-register").val();
   //     var reg = /^[a-zA-Z0-9_]{5,15}$/;//5到15位的英文和符号
   //     if(reg.test(password))
   //     {
   //         finish2 = true;
   //         $(".password-safe p").css("display","block");
   //         $(".password-safe div").css("display","block");
   //         $(".error-message.error-message-2").html("");
   //     }
   //     else
   //     {
   //         finish2 = false;
   //         $(".password-safe p").css("display","none");
   //         $(".password-safe div").css("display","none");
   //         $(".error-message.error-message-2").html("密码为5-15位的英文字符");
   //     }
   // });
   // $("#code-register").change(function () {
   //    var username = $("#code-register").val();
   //    var reg = /^[0-9_]{11}$/;
   //    if(reg.test(username))
   //    {
   //        finish3 = true;
   //        $(".error-message.error-message-3").html("");
   //    }
   //    else
   //    {
   //        finish3 = false;
   //        $(".error-message.error-message-3").html("请输入11位的手机号");
   //
   //    }
   // });
   // //同意协议按钮
   // $(".register-agree .check-box").click(function () {
   // //    检查是否含有check-box-disable
   //     if($(this).hasClass("check-box-disable"))
   //     {
   //         $(".register-agree .check-box").removeClass("check-box-disable");
   //         $(".register-agree .check-box").addClass("check-box-able");
   //         $(".el-button.btn-full").removeClass("btn-disable");
   //         $(".el-button.btn-full").addClass("btn-agree");
   //     }
   //     else
   //     {
   //         $(".register-agree .check-box").removeClass("check-box-able");
   //         $(".register-agree .check-box").addClass("check-box-disable");
   //         $(".el-button.btn-full").removeClass("btn-agree");
   //         $(".el-button.btn-full").addClass("btn-disable");
   //     }
   // });
   //
   // $("#register-btn").click(function () {
   //     if($(".register-agree .check-box").hasClass("check-box-able"))
   //     {
   //         if(finish1&&finish2&&finish3)
   //         {
   //             var username = $("#code-register").val();
   //             var nickname = $("#nickname-register").val();
   //             var password = $("#password-register").val();
   //             $.ajax(
   //                 {
   //                     url:'http://localhost:8080/register/login/register',
   //                     type:'POST',
   //                     data:{
   //                         username:username,
   //                         nickname:nickname,
   //                         password:password
   //                     },
   //                     success:function (data) {
   //                     //    清除浏览器中的localStorage
   //                         localStorage.clear();
   //                         localStorage.setItem("iflogin",true);
   //                         localStorage.setItem("username",username);
   //                         window.location.href = 'changeFace.html';
   //                     }
   //                 }
   //             )
   //         }
   //         else
   //         {
   //             alert("请认真填写信息")
   //         }
   //     }
   //     else
   //     {
   //         alert("请先同意协议");
   //     }
   // })
});