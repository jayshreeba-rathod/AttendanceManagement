<html lang="en">
<%@ include file="cssInclude.jsp"%>
<body>
	<%@ include file="headerInclude.jsp"%>
	<section class="reportsSection">
		<div class="row col-sm-12">
			<h3 class="col-sm-6">Employee's Attendance Report</h3>
			<div class="col-sm-5">
				<button type="button" class="btn btn-success col-sm-2 reportType"
					value="Daily">Daily</button>
				<button type="button" class="btn btn-success col-sm-2 reportType"
					value="Weekly">Weekly</button>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-10"
				style="padding: 10px; margin: 5px; border-radius: .3rem;">
				<form method="post" action="getEmployeeInfo" id="dailyEmployeeForm">
					<div class="form-group selectDateDiv">
						<label class="form-control-label col-sm-2">Select Date</label> <input
							type="date" class="form-control col-sm-3" name="dailyReportDate"
							id="dailyReportDate" pattern="\d{4}-\d{2}-\d{2}" required>
						<div class="col-sm-1"></div>
						<input type="submit" value="Submit"
							class="btn btn-success col-sm-2">
					</div>
				</form>
			</div>

		</div>
		<div class="row">
			<div class="col-sm-10"
				style="padding: 10px; margin: 5px; border-radius: .3rem;">
				<form method="post" action="getEmployeeInfo" id="weeklyEmployeeForm">
					<div class="form-group selectDateRangeDiv">
						<label class="form-control-label col-sm-3"
							style="align-items: center; display: flex;">Select Date
							Range</label> <input class="form-control" name="weeklyReportDate"
							id="weeklyReportDate"> <input type="submit"
							value="Submit" class="btn btn-success col-sm-2 submitButton">
						<div class="col-sm-3 message"></div>

					</div>
				</form>
			</div>

		</div>
		<div class="row">
			<div class="col-sm-12">
				<table id="reportsTable" class="table table-striped table-bordered"
					style="width: 100% !important">
					<thead>
						<tr>
							<th>Employee Name</th>
							<th>Punch Date</th>
							<th>Punch Time</th>
							<th>Present</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>

	</section>

	<%@ include file="footer.jsp"%>

</body>

</html>