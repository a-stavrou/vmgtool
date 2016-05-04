<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ include file="../layout/taglib.jsp" %>

			<script type="text/javascript">
			$(document).ready(function() {
				$('.nav-tabs a:first').tab('show');
				$(".triggerRemove").click(function(e) {
					e.preventDefault();
					$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
					$("#modalRemove").modal();
				});
			});
			
			
			function edit(id,name,email){
			    document.getElementById("userform").reset();
				 $('#userform')
			     .find('[name="name"]').val(name).end()
			     .find('[name="email"]').val(email).end()
			     .find('[name="id"]').val(id).end();
			
			}
			</script>

			<form:form commandName="user" id="userform" cssClass="form-horizontal"
				action="users/edit" method="POST">
			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			        
			      </div>
			      <div class="modal-body">
			
					<form:hidden path="id" name="id" value="" cssClass="form-control" />
			
			
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Name:</label>
						<div class="col-sm-10">
							<form:input path="name" name="name" cssClass="form-control" />
						</div>
					</div>
					
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Email:</label>
						<div class="col-sm-10">
							<form:input path="email" name="email" cssClass="form-control" />
						</div>
					</div>
			
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			        <input type="submit" class="btn btn-primary" value="Save" />
			      </div>
			    </div>
			  </div>
			</div>
			</form:form>

          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Users </span></div>
              
		<table class="table table-bordered table-hover table-striped">
			<thead>
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
					   <td>
								${user.name}
						</td>
						
					   <td>
								${user.email}
						</td>
						<td>
						<button data-toggle="modal" class="btn-edit btn btn-success custom-width"
					 		onclick="edit('${user.id}','${user.name}','${user.email}')" data-target="#myModal">edit</button>
						
						 <a href="<spring:url value="users/remove/${user.id}" /> " class="btn btn-danger">remove</a>
						</td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>


</div>