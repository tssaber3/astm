$(function () {
    //先查看是否有user 再将其发送给后台校验 查看他的权限 再展示不同的页面
    var username = localStorage.getItem("username");
    $.ajax(
        {
            url:'http://localhost:8090/user/getreportbyname',
            type:"post",
            dataType:'json',
            async: false,
            data:
                {
                    username:username
                },
            success:function (data) {
                var list = data.report;
                for (var i = 0;i < list.length;i++)
                {
                    $(".project-list").append('<div class="project-info">\n' +
                        '                <div class="project-info-project_name">'+ list[i].project.project_name +'</div>\n' +
                        '                <div class="project-info-user_nickname">'+ list[i].stu_nickname +'</div>\n' +
                        '                <div class="project-info-user_name">'+ list[i].stu_username  +'</div>\n' +
                        '                <div class="project-info-project_type">'+ list[i].project.type +'</div>\n' +
                        '                <div class="project-info-project_credit">'+ list[i].credit +'</div>\n' +
                        '                <div class="project-info-project_proof">'+ list[i].proof +'</div>\n' +
                        '                <div style="display: none" id="project-info-project_id">'+ list[i].id +'</div>\n' +
                        '            </div>')
                }
            },
            error:function (data) {

            }
        }
    );
    $(".btn-back").click(function () {
        window.location.href = 'info.html';
    });
});