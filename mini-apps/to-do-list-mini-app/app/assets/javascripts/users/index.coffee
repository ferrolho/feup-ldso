$ ->
  $.get "/get-users", (users) ->
    $.each users, (index, user) ->
      name = $("<div>").addClass("name").text user.name
      $("#users").append $("<li>"),append(name)