<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 9/14/2018
  Time: 10:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css"
          integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
</head>
<body>
<h1>Motorbike</h1>
<form id="logoutForm" method="POST" action="/logout">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
    <input type="submit" value="log out">
</form>
<div class="container mt-5">

    <button type="button" class="btn btn-primary mb-5 " data-toggle="modal" data-target="#form-modal">
        Add New Motorbike
    </button>

    <div id="respond-success" class="alert alert-success d-none">
        ACTION SUCCESS!!!!!!!!!
    </div>
    <table id="motorbike-list" class="table text-center mb-5">
        <thead class="thead-dark">
        <tr>
            <th>Name</th>
            <th>Date Import</th>
            <th>Available</th>
            <th>Price</th>
            <th>Manufactory</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody id="table-body">
        <c:forEach items="${motorbikePage.content}" var="motorbike">
            <tr>
                <td>${motorbike.name}</td>
                <td>${motorbike.date}</td>
                <td>
                    <c:choose>
                        <c:when test="${motorbike.available==true}">
                            yes
                        </c:when>
                        <c:otherwise>
                            no
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${motorbike.price}</td>
                <td>${motorbike.manufactory.manufactoryName}</td>
                <td>
                    <button id="delete-${motorbike.id}" class="btn btn-danger delete-motorbike"
                            data-item-id="${motorbike.id}">delete
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <nav class="paging" id="paging">
        <ul class="pagination text-primary">
            <li class="page-item ${motorbikePage.hasPreviousPage()==true?"":"disabled"} ">
                <a class="page-link" id="page-previous">previous</a>
            </li>

            <c:forEach begin="1" end="${motorbikePage.totalPages}" var="page">
                <li class="page-item ${motorbikePage.number+1 == page?"active text-white":""}">
                    <a class="page-link" id="page-${page}">${page}</a>
                </li>
            </c:forEach>

            <li class="page-item  ${motorbikePage.hasNextPage()==true?"":"disabled"}">
                <a class="page-link" id="page-next">Next</a>
            </li>
        </ul>
    </nav>

    <!-- Modal -->
    <div class="modal fade" id="form-modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">New Motorbike</h5>
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form id="add-motorbike-form">
                    <div class="modal-body">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text px-4"><i class='fas fa-motorcycle'></i></span>
                            </div>
                            <input type="text" class="form-control" id="mtName" placeholder="Motorbike Name"/>
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text px-4"><i class='far fa-calendar-alt'></i></span>
                            </div>
                            <input type="date" class="form-control" id="mtDateImport">
                        </div>

                        <div class="form-check mb-3">
                            <input type="checkbox" class="form-check-input" id="mtAvailable">
                            <label class="form-check-label" for="mtAvailable">Yes</label>
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text px-4"><i class="fa fa-dollar"></i></span>
                            </div>
                            <input class="form-control" id="mtPrice" placeholder="Price">
                        </div>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="manufactory">Manufactory</label>
                            </div>
                            <select class="custom-select" id="manufactory">
                                <c:forEach items="${manufactories}" var="manufactory">
                                    <option value="${manufactory.id}">${manufactory.manufactoryName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer text-center">

                        <button type="submit" class="btn btn-primary btn-block">Add</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>

<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        requestAdd();
        requestDelete();
        requestGoToPage();
    });


    function requestGoToPage() {
        $(".page-link").on('click', function (event) {
            var data = prepareDataBeforeChangePage(event);
            console.log(data);
            createGoToPageAjax(data);
        })
    }

    function prepareDataBeforeChangePage(event) {
        var strRequestPageNumber = event.target.id;
        var strCurrentPageNumber = $('li.active > a').attr('id');
        var requestParams = "strRequestPageNumber=" + strRequestPageNumber + "&" + "strCurrentPageNumber=" + strCurrentPageNumber;
        console.log(requestParams);
        return requestParams;
    }

    function createGoToPageAjax(data) {
        $.ajax({
            Type: "GET",
            url: "${pageContext.request.contextPath}/motorbike/page",
            data: data,
            success: function (motorbikePage) {
                console.log(motorbikePage)
                renderMotorbikeTable(motorbikePage);
                updatePagingBar(motorbikePage);
            }
        })
    }

    function renderMotorbikeTable(motorbikePage) {
        var tableBody = $('#table-body');
        tableBody.children().remove();
        motorbikePage.content.forEach(function (motorbike){
            renderMotorbikeRow(motorbike);
        });
    }

    function updatePagingBar(motorbikePage) {
        if(motorbikePage.lastPage===true){
            $('#page-next').parent().addClass("disabled");
            $('#page-previous').parent().removeClass("disabled");
        }else if(motorbikePage.firstPage===true){
            $('#page-next').parent().removeClass("disabled");
            $('#page-previous').parent().addClass("disabled");
        }else {
            $('#page-next').parent().removeClass("disabled");
            $('#page-previous').parent().removeClass("disabled");
        }

        $('li.active').removeClass('active text-white');
        var currentPage = "#page-"+(motorbikePage.number+1);
        $(currentPage).parent().addClass('active text-white');

    }

    function requestAdd() {
        $("#add-motorbike-form").submit(function (event) {
            event.preventDefault();
            var formData = handleAddFormSubmit();
            createAddMotorbikeAjax(formData);
        })
    }

    function handleAddFormSubmit() {
        var name = $("#mtName").val();
        var dateImport = $("#mtDateImport").val();
        var available = $("#mtAvailable").is(":checked");
        var price = $("#mtPrice").val();
        var manufactoryId = $("#manufactory").val();
        var formData = convertFormDataToJSON(name, dateImport, available, price, manufactoryId);
        console.log(formData);
        return formData;
    }

    function convertFormDataToJSON(name, dateImport, available, price, manufactoryId) {
        return JSON.stringify(
            {
                'name': name,
                'date': dateImport,
                'available': available,
                'price': price,
                'manuId': manufactoryId
            }
        );
    }

    function createAddMotorbikeAjax(formData) {
        $.ajax({
            type: 'POST',
            url: "${pageContext.request.contextPath}/motorbike/add",
            contentType: "application/json",
            data: formData,
            success: function (dataMotorbike) {
                renderMotorbikeRow(dataMotorbike);
                saySuccess();
                hideModal()
            }
        })
    }

    function renderMotorbikeRow(dataMotorbike) {
        if (dataMotorbike == null) {
            return;
        }
        var row = $("<tr>");

        var tdName = $("<td>");
        tdName.text(dataMotorbike.name);
        var tdDate = $("<td>");
        tdDate.text(dataMotorbike.date);
        var tdAvailable = $("<td>");
        if (dataMotorbike.available === true) {
            tdAvailable.text("yes");
        } else {
            tdAvailable.text("no");
        }
        var tdPrice = $("<td>");
        tdPrice.text(dataMotorbike.price);
        var tdManufactory = $("<td>");
        tdManufactory.text(dataMotorbike.manufactory.manufactoryName);
        var tdDeleteButton = $("<td>");
        var deleteButton = $("<button>");
        deleteButton.attr({
            id: "delete" + dataMotorbike.id,
        });
        deleteButton.attr('data-item-id', dataMotorbike.id);
        deleteButton.addClass('btn btn-danger delete-motorbike');
        deleteButton.text('delete');
        tdDeleteButton.append(deleteButton);

        row.append(tdName);
        row.append(tdDate);
        row.append(tdAvailable);
        row.append(tdPrice);
        row.append(tdManufactory);
        row.append(tdDeleteButton);
        $("#table-body").append(row);
    }

    function hideModal() {
        $('#form-modal').modal('hide');
    }

    function requestDelete() {
        $("#motorbike-list").on('click', '.delete-motorbike', function (event) {
            var id = $(event.target).data("itemId");
            var data = JSON.stringify({"id": id});
            var rowId = "#" + event.target.id;
            console.log("into: delete event");
            createDeleteMotorbikeAjax(data, rowId)
        });
    }

    function createDeleteMotorbikeAjax(data, rowId) {
        $.ajax({
            type: 'DELETE',
            url: "${pageContext.request.contextPath}/motorbike/delete",
            contentType: "application/json",
            data: data,
            success: function (data) {
                removeFromTableMotorbike(data, rowId);
                saySuccess();
            }
        });
    }

    function removeFromTableMotorbike(data, rowId) {
        $(rowId).closest("tr").remove();

    }

    function saySuccess() {
        $("#respond-success").removeClass("d-none");
        setTimeout(
            function () {
                $("#respond-success").addClass("d-none");
            }
            , 2000);
    }
</script>

</body>
</html>
