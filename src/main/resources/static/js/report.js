$(function () {
    //先查看是否有user 再将其发送给后台校验 查看他的权限 再展示不同的页面
    var username = localStorage.getItem("username");

    $.ajax(
        {
            url: 'http://localhost:8090/user/iflogin',
            type: 'POST',
            dataType: 'JSON',
            // xhrFields: {withCredentials: true},
            data: {
                username: username
            },
            success: function (data) {
                $(".info-item-stu_username").text(data.username);
                $(".info-item-stu_nickname").text(data.nickname);
            }
        }
    );

    $(".info-item-content-deparment").click(function () {
       $(".drop-cascader-container").css("display","block");
    });


//    取消按钮
    $("#cascader-cancel").click(function () {
        $(".drop-cascader-container").css("display","none");
    });
    
    $(".cascader-item").click(function () {
        $("#info-item-content-deparment").html($(this).children("p").html());
        $(".drop-cascader-container").css("display","none");
    });


//    提交按钮
    $(".btn-shenghe").click(function () {
        var bok = true;
        if($(".content-name-textarea").val() == '')
        {
            $(".project_name_error").css("display","inline-block");
            bok = false;
        }else
        {
            $(".project_name_error").css("display","none");
        }
        if($(".content-credit-textarea").val() == '')
        {
            $(".credit_error").css("display","inline-block");
            bok = false;
        }else
        {
            $(".credit_error").css("display","none");
        }
        if($(".info-item-content-deparment").text() == '点击选择' ||$(".info-item-content-deparment").text() == '')
        {
            $(".project_type_error").css("display","inline-block");
            bok = false;
        }else
        {
            $(".project_type_error").css("display","none");
        }
        if($(".content-description-textarea").val() == '')
        {
            $(".description_error").css("display","inline-block");
            bok = false;
        }else
        {
            $(".description_error").css("display","none");
        }

        if(bok)
        {
            var username = $(".info-item-stu_username").text();
            var nickname = $(".info-item-stu_nickname").text();
            var project_name = $(".content-name-textarea").val();
            var credit = $(".content-credit-textarea").val();
            var type = $("#info-item-content-deparment").text();
            var description = $(".content-description-textarea").val();

            $.ajax(
                {
                    url: 'http://localhost:8090/user/uploadreport',
                    type:'post',
                    dataType:'text',
                    data:{
                        username:username,
                        nickname:nickname,
                        project_name:project_name,
                        credit:credit,
                        type:type,
                        description:description
                    },
                    success:function (data) {
                        //返回true表示成功 false添加失败
                        if(data)
                        {
                            alert("成功,即将返回");
                            window.location.href = 'info.html';
                        }
                    }
                }
            )
        }

    });
});