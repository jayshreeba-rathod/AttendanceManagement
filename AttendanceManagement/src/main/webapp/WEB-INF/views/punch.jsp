<html lang="en">
<%@ include file="cssInclude.jsp"%>
<body>
	<%@ include file="headerInclude.jsp"%>

	<section class="punchSection">
		<div class="row">
			<h3>
				Welcome ${empName} , <span>Please Punch In ! </span>
			</h3>

		</div>
		<div class="row">
			<button type="button" class="btn btn-success punchIn"
				title="Punch in your arrival">Punch In</button>
		</div>
		<div class="row message"></div>

	</section>

	<%@ include file="footer.jsp"%>

</body>

</html>