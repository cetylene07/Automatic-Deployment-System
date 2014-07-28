<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/common.jsp"%>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script>
	$(document).ready(function() {

		// 파일 추가
	    $('#addFile').click(function() {
	        var fileIndex = $('#fileTable tr').children().length - 1;
	        $('#fileTable').append(
	                '<tr><td>'+
	                '   <input type="file" name="files['+ fileIndex +']" />'+
	                '</td></tr>');
	    });

	});
</script>

<h1>Spring Multiple File Upload example</h1>

<form:form method="post" action="/file/upload" enctype="multipart/form-data">

    <p>Select files to upload. Press Add button to add more file inputs.</p>

    <input id="addFile" type="button" value="Add File" />
    <table id="fileTable">
        <tr>
            <td><input name="files[0]" type="file" /></td>
        </tr>
        <tr>
            <td><input name="files[1]" type="file" /></td>
        </tr>
    </table>
    <br/><input type="submit" value="Upload" />
</form:form>
