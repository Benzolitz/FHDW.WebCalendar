$(document).ready(function() {
  getEventData();
  setCharatersLeft(parseInt($("#txaEventComment").attr("maxlength")));
  $("#txaEventComment").keyup(countKeysLeft());

  $(document).on('click', '#startClock', function() {
    NewCal("txtEventStart", "ddmmyyyy", true, 24);
  });
  $(document).on('click', '#endClock', function() {
    NewCal("txtEventEnd", "ddmmyyyy", true, 24);
  });
});

var getEventData = function() {
  var parameters = getURLParameters();

  if (parameters.id != -1) {

    $.ajax({
      type: "POST",
      url: "EventController",
      dataType: "json",
      data: {
        action: "getEventData",
        eventId: parameters.id
      },
      success: function(response) {
        $("#txtEventTitle").val(response.title);

        $("#txtEventCategory").val(response.category.join(", "));
        $("#txtEventStart").val(buildDateTime(response.startTime));
        $("#txtEventEnd").val(buildDateTime(response.endTime));
        $("#txtEventLocation").val(response.location);

        $("#txtEventRequiredGuests").val(response.requiredUser.join(", "));
        $("#txtEventOptionalGuests").val(response.optionalUser.join(", "));
      },
      error: function(jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseText);
      }
    });
  }
};

var buildDateTime = function(time) {
  var day = time.dayOfMonth < 10 ? "0" + time.dayOfMonth : time.dayOfMonth;
  var month = time.month < 10 ? "0" + time.month : time.month;
  var year = time.year;

  var hour = time.hourOfDay < 10 ? "0" + time.hourOfDay : time.hourOfDay;
  var minute = time.minute < 10 ? "0" + time.minute : time.minute;
  var second = time.second < 10 ? "0" + time.second : time.second;

  return day + "." + month + "." + year + " " + hour + ":" + minute + ":"
          + second;
};

/* http://stackoverflow.com/a/979995 */
var getURLParameters = function() {
  var query_string = {};
  var query = window.location.search.substring(1);
  var vars = query.split("&");
  for (var i = 0; i < vars.length; i++) {
    var pair = vars[i].split("=");
    if (typeof query_string[pair[0]] === "undefined") {
      query_string[pair[0]] = pair[1];
    } else if (typeof query_string[pair[0]] === "string") {
      var arr = [query_string[pair[0]], pair[1]];
      query_string[pair[0]] = arr;
    } else {
      query_string[pair[0]].push(pair[1]);
    }
  }
  return query_string;
};

var setCharatersLeft = function(p_charCount) {
  $("#spaChars").text(p_charCount);
};

var countKeysLeft = function() {
  var maxLength = $("#txaEventComment").attr("maxlength");
  var charsLeft = parseInt(maxLength) - $("#txaEventComment").val().length;

  setCharatersLeft(charsLeft);
};

var addFileToEvent = function() {
};

var saveEvent = function() {
  $.ajax({
    type: "POST",
    url: "EventController",
    dataType: "json",
    data: {
      action: "saveEvent",
      title: $("#txtEventTitle").val(),
    },
    success: function(response) {
    },
    error: function(jqXHR, textStatus, errorThrown) {
      alert(jqXHR.responseText);
    }
  });
};

var deleteEvent = function() {
  var parameters = getURLPa rameters();

  if (parameters.id != -1) {

    $.ajax({
      type: "POST",
      url: "EventController",
      dataType: "json",
      data: {
        action: "deleteEvent",
        eventId: parameters.id
      },
      success: function(response) {
      },
      error: function(jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseText);
      }
    });
  }
};