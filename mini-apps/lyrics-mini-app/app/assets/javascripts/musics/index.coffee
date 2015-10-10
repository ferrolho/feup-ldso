$ ->
  $.get "/get-musics", (musics) ->
    musics.sort (a, b) ->
      return if a.title.toUpperCase() >= b.title.toUpperCase() then 1 else -1

    $.each musics, (index, music) ->
      title = $("<a>").attr("href", "musics/" + music.id).addClass("title").text music.title
      lyrics = $("<div>").addClass("lyrics").text music.lyrics
      year = $("<div>").addClass("year").text music.year

      $("#musics").append $("<li>").append(title) #.append(lyrics).append(year)
