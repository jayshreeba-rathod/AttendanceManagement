<html lang="en">
<%@ include file="cssInclude.jsp"%>
<body>
	<%@ include file="headerInclude.jsp"%>

	<section>
		<div id="loginForm" class="col-sm-12">
			<div class="modal-dialog" style="top: 15%; width: 30%">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Welcome back ! Please Login</h5>
					</div>
					<div class="modal-body">
						<div class="modal-box">
							<div class="row">
								<form:form class="col-sm-12" method="post" action="/login"
									modelAttribute="employees">
									<form:errors path="empName" cssClass="error" />

									<div class="form-group">
										<form:input path="empName" class="form-control"
											placeholder="Employee Name" required="required" />
									</div>
									<div class="form-group">
										<form:input path="password" type="password"
											class="form-control" placeholder="Password"
											required="required" />
									</div>
									<div class="form-group">
										<input type="submit" value="Login" class="btn btn-success" />
									</div>
								</form:form>
							</div>

						</div>

					</div>
				</div>

			</div>

		</div>
	</section>

	<%@ include file="footer.jsp"%>

</body>

</html>