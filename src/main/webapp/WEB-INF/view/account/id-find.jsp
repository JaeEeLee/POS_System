<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../comm/header.jsp"/>

<div class="jumbotron">
    <div class="container">
        <h1 class="display-4 text-center">아이디 찾기</h1>
    </div>
</div>

<div class='container' style="max-width: 500px">


                <form method="post">

                    <div class="form-group">
                            <label for="email">이메일을 입력해주세요.</label>
                            <input class="form-control" type="text" id="email" name="email" required="required" placeholder="이메일">
                     <span id="idfindresult"></span>
                    </div>


                        <div class="form-group">
                            <input class="btn btn-primary" style="display: none" value="확인" id="idfindconfirm" onclick="history.go(-1);"/>
                         <input class="btn btn-primary" style="display: inline"  type="button" value="아이디찾기" id="idfind" onclick="idFind()"/>
                            <button class="btn btn-secondary" style="display: inline" id="cancel" type="button" onclick="history.go(-1);">Cancel</button>

                    </div>
                </form>


        </div>





<jsp:include page="../comm/footer.jsp"/>