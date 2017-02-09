<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link
	href="resources/bootstrap/bootstrap-3.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="resources/jquery/jquery-3.1.1.min.js"
	type="text/javascript"></script>
<script type="text/javascript">

</script>
<html>
<head>
<title>로그인</title>
</head>
<body>
 
 <div class="container-fluid">
                <div class="row-fluid" >
                   
                      
                     <div class="col-md-offset-4 col-md-4" id="box">
                      <h2>Login</h2>
                       
                            <hr>
                           

                                <form class="form-horizontal" action=" " method="" id="contact_form">
                                    <fieldset>
                                        <!-- Form Name -->


                                        <!-- Text input-->

                                        <div class="form-group">

                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                                    <input name="first_name" placeholder="Username" class="form-control" type="text">
                                                </div>
                                            </div>
                                        </div>


                                  
                                        <!-- Text input-->
                                        <div class="form-group">

                                            <div class="col-md-12">
                                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                                    <input name="email" placeholder="Password" class="form-control" type="text">
                                                </div>
                                            </div>
                                        </div>


                                    
                                   
                                        <div class="form-group">

                                            <div class="col-md-12">
                                                <button type="submit" class="btn btn-md btn-danger pull-right">Login </button>
                                            </div>
                                        </div>

                                    </fieldset>
                                </form>
                    </div> 
</div>
</body>
</html>
