$ ->
  $.get "/get-albums", (albums) ->
    $.each albums, (index, album) ->
      name = $("<div>").addClass("name").text album.name
      description = $("<div>").addClass("description").text album.description
      $("#albums").append $("<li>").append(name).append(description)
