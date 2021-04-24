<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="" class="modal fade" role="dialog">
	<div class="modal-dialog" style="top: 15%; width: 30%">
		<div class="modal-content">

			<div class="modal-header">
				<h5 class="modal-title">Let's setup your account</h5>
				<button type="button" class="close closeModal" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<div class="modal-box">
					<div class="row">
						<form:form class="col-sm-12" method="post" action="/signUpSubmit"
							modelAttribute="employee">
							<div class="form-group">
								<form:label path="empName" class="form-control-label">Employee Name</form:label>
								<form:input path="empName" class="form-control"
									placeholder="Enter your name" required="required" />
								<form:errors path="empName" cssClass="error" />
							</div>
							<div class="form-group">
								<form:label path="password" class="form-control-label">Password</form:label>
								<form:input path="password" type="password" class="form-control"
									placeholder="Enter your password" required="required" />
								<form:errors path="password" cssClass="error" />
							</div>
							<div class="form-group">
								<form:label path="position" class="form-control-label">Position<span
										class="col-sm-1"></span>
								</form:label>
								<div class="form-check form-check-inline">
									<form:radiobutton path="position" class="form-check-input"
										value="Employee" required="required" />
									Employee
								</div>
								<div class="form-check form-check-inline">
									<form:radiobutton path="position" class="form-check-input"
										value="Manager" required="required" />
									Manager
								</div>
							</div>
							<div class="form-group">
								<input type="submit" value="Sign Up" class="btn btn-success" />
							</div>
						</form:form>
					</div>

				</div>

			</div>
		</div>

	</div>

</div>