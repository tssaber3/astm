$(function () {
    //先查看是否有user 再将其发送给后台校验 查看他的权限 再展示不同的页面
    var username = localStorage.getItem("username");
    //老师可以查看的类型
    var type;
    $.ajax(
        {
            url:'http://localhost:8090/user/iflogin',
            type:'POST',
            dataType:'JSON',
            async:false,
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
                    if (name == '老师')
                    {
                        $(".item-tea_nickname").html(data.username);
                        type = data.type;
                    }else
                    {
                        window.location.href = 'login.html';
                    }
                }
            }
        }
    );
    
    //再请求审核列表
    $.ajax(
        {
            url:'http://localhost:8090/user/getList',
            type:'post',
            dataType:'JSON',
            data:{
                type:type
            },
            async: false,
            success:function (data) {
                for (var i = 0;i < data.report.length;i++)
                {
                    $(".project-list").append('<div class="project-info">\n' +
                        '                <div class="project-info-project_name">'+ data.report[i].project.project_name +'</div>\n' +
                        '                <div class="project-info-user_nickname">'+ data.report[i].stu_nickname +'</div>\n' +
                        '                <div class="project-info-user_name">'+ data.report[i].stu_username +'</div>\n' +
                        '                <div class="project-info-project_type">'+ data.report[i].project.type +'</div>\n' +
                        '                <div class="project-info-project_credit">'+ data.report[i].credit +'</div>\n' +
                        '                <div class="project-info-project_proof">'+ data.report[i].proof +'</div>\n' +
                        '            </div>')
                }
            }
        }
    );

    $(".project-info").click(function () {
        var project_name = $(this).children(".project-info-project_name").text();
        var user_name = $(this).children(".project-info-user_name").text();
        var project_type = $(this).children(".project-info-project_type").text();
        var project_credit = $(this).children(".project-info-project_credit").text();
        $.ajax(
            {
                url:'http://localhost:8090/user/getreportbyall',
                type:'post',
                dataType:'JSON',
                async: false,
                data:{
                    project_name:project_name,
                    user_name:user_name,
                    project_type:project_type,
                    project_credit:project_credit,
                },
                success:function (data) {
                    var id = data.id;
                    window.location.href = "report_view.html?id=" + id;
                },
                error:function (data) {
                    alert("失败");
                }
            }
        );

    })
});