$(document).ready(function() {
  getEventData();
  setCharatersLeft(parseInt($("#txaEventComment").attr("maxlength")));
  $("#txaEventComment").keyup(countKeysLeft());

  $(document).on('click', '#startClock', function() {
    NewCal("txtEventStart", "ddmmyyyy", true, 24)
  });
  $(document).on('click', '#endClock', function() {
    NewCal("txtEventEnd", "ddmmyyyy", true, 24)
  });
});

var getEventData = function() {
  var parameters = getURLParameters();

  if (parameters.event != "-1") {

    $
            .ajax({
              type: "POST",
              url: "EventController",
              dataType: "json",
              data: {
                Action: "getEventData",
                eventId: parameters.event
              },
              success: function(response) {
                var parameters = getURLParameters();
                $("#txtEventTitle").val(
                        response.title === null ? "" : response.title);

                $("#txtEventCategory").val(
                        response.category === null ? "" : response.category
                                .join(", "));
                $("#txtEventStart").val(
                        response.startTime === null ? ""
                                : buildDateTime(response.startTime));
                $("#txtEventEnd").val(
                        response.endTime === null ? ""
                                : buildDateTime(response.endTime));
                $("#txtEventLocation").val(
                        response.location === null ? "" : response.location);

                var required = "";
                if (response.requiredUser != null) {

                  for (var i = 0; i < response.requiredUser.length; i++) {

                    if (required === "") {
                      required = response.requiredUser[i];
                    } else {
                      required += ", " + response.requiredUser[i];
                    }
                  }
                }

                $("#txtEventRequiredGuests").val(required);

                var optional = "";
                if (response.optionalUser != null) {
                  for (var i = 0; i < response.optionalUser.length; i++) {

                    if (optional === "") {
                      optional = response.optionalUser[i];
                    } else {
                      optional += ", " + response.optionalUser[i];
                    }
                  }
                }

                $("#txtEventOptionalGuests").val(optional);

                $("#txaEventComment").val(unescape(response.message));
              },
              error: function(jqXHR, textStatus, errorThrown) {
              }
            });
  }
};

var buildDateTime = function(time) {
  var day = time.dayOfMonth < 10 ? "0" + time.dayOfMonth : time.dayOfMonth;
  var month = time.month + 1;
  month = month < 10 ? "0" + month : month;
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
  var parameters = getURLParameters();
  $.ajax({
    type: "POST",
    url: "EventController",
    dataType: "json",
    data: {
      Action: "SaveEvent",
      CalendarId: parameters.calendar,
      UserId: parameters.user,
      EventId: parameters.event,
      EventTitle: $("#txtEventTitle").val(),
      EventCategories: $("#txtEventCategory").val(),
      EventStartTime: $("#txtEventStart").val(),
      EventEndTime: $("#txtEventEnd").val(),
      EventLocation: $("#txtEventLocation").val(),
      EventRequiredGuests: $("#txtEventRequiredGuests").val(),
      EventOptionalGuests: $("#txtEventOptionalGuests").val(),
      EventComment: $("#txaEventComment").val()
    },
    success: function(response) {
    },
    error: function(jqXHR, textStatus, errorThrown) {
      window.close();
    }
  });
};

var deleteEvent = function() {
  var parameters = getURLParameters();

  if (parameters.event != "-1") {

    $.ajax({
      type: "POST",
      url: "EventController",
      dataType: "json",
      data: {
        Action: "deleteEvent",
        eventId: parameters.event,
        calendarId: parameters.calendar
      },
      success: function(response) {
        window.close();
      },
      error: function(jqXHR, textStatus, errorThrown) {
      }
    });
  }
};