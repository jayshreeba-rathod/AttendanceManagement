<script type="text/javascript">
	$(document).ready(function() {
		$("#dailyReportDate").attr("max", new Date().toISOString().split('T')[0]);
		var table;
		$(document).on("click", ".punchSection .punchIn", function() {

			$.ajax({
				url : "punchTime",
				type : "POST",
				success : function(response) {
					$(".punchSection  .message").text(response);

				},
				error : function(error) {

				}
			});

		});
		$(document).on("click", "button.reportType", function() {
			var reportType = $(this).attr("value");
			if (reportType == "Daily") {
				$("input#dailyReportDate").val("");
				$(".selectDateDiv").css("display", "flex");
				$(".selectDateRangeDiv").css("display", "none");
			} else {
				$("input#weeklyReportDate").val("");
				$(".selectDateRangeDiv").css("display", "flex");
				$(".selectDateDiv").css("display", "none");
			}

		});

		table = $('#reportsTable').DataTable({
			"ajax" : {
				"url" : "/getEmployeeInfo",
				"type" : "POST",
				"data" : function(d) {
					d.dailyReportDate = $("input#dailyReportDate").val();
					var weeklyReportDate = $("input#weeklyReportDate").val();
					var start = "";
					var end = "";
					if(weeklyReportDate != ""){
						weeklyReportDate = JSON.parse(weeklyReportDate);
						start = weeklyReportDate.start;
						end = weeklyReportDate.end
					}
					d.start = start;
					d.end = end;
				}
			},
			"fnDrawCallback" : function( row, data, dataIndex ) {
				 	var tableRows = $('#reportsTable').children("tbody").children("tr");
				 	tableRows.each(function () {
					var getSpan = $(this).find("td:eq(3)").find("span");

					if (getSpan.hasClass("empPresent")) {
						$(this).find("td:eq(3)").css("background-color", "green");
					}
					if (getSpan.hasClass("empAbsent")) {
						$(this).find("td:eq(3)").css("background-color", "red");
					}

					});
			}
					
		});
		$("#dailyEmployeeForm").submit(function(event){
			event.preventDefault();
			$("input#weeklyReportDate").val("");
			table.ajax.reload(null,false);
		});

		$("#weeklyEmployeeForm").submit(function(event){
			event.preventDefault();
			$("input#dailyReportDate").val("");
			var weeklyReportDate = $("input#weeklyReportDate").val();
			if(weeklyReportDate == ""){
				$(".selectDateRangeDiv .message").text("Fill in Date Range");
				return false;
			}else{
				$(".selectDateRangeDiv .message").text("");
			}
			
			table.ajax.reload(null,false);
		});

		
		$("#weeklyReportDate").daterangepicker({
			datepickerOptions : {
				numberOfMonths : 2
			},
			initialText : 'Select period...'

		});



		
});
</script>