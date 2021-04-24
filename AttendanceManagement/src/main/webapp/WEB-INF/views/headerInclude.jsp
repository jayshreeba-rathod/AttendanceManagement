<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<nav class="navbar navbar-expand" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="/"
					title="Attendance Management System"> Attendance Management</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-right nav-box">
					<li><a href="/" id="homeButton">Home</a></li>
					<li><a href="/about">About </a></li>
					<li><a href="/contact">Contact</a></li>
					<c:if test="${empPosition != null && empPosition eq 'Manager'}">
						<li><a href="/reports">Reports</a></li>
					</c:if>
					<c:if test="${empName != null}">
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown"><i class="fa fa-user"
								aria-hidden="true"></i></a>
							<ul class="dropdown-menu dropdown-menu-right">
								<li><div class="dropdown-header">
										<h6 class=>Welcome ${empName  != null ? empName  : "Guest"}
											!</h6>
									</div>
									<div class="col-sm-12 ">
										<a href="/logout" class="btn btn-default btn-flat">Sign
											out</a>
									</div></li>
							</ul></li>
					</c:if>
				</ul>
				<c:if test="${empName == null}">
					<a href="/login" class="btn btn-success btn-flat">Login</a>
				</c:if>
			</div>
		</div>

	</nav>
</header>