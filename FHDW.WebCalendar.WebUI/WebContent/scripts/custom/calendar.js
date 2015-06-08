var dayColumnWidthArray = [];

$(document).ready(function() {
  {
    buildUserCalendar(-1, new Date().getFullYear());
    $(window).resize(resizingCorrection);

    $("input[name=Calendar]:radio").change(function() {
      var currentWeek = parseInt($("#hidCurrentWeek").val());
      var currentYear = parseInt($("#hidCurrentYear").val());
      buildUserCalendar(currentWeek, currentYear);
    });
  }
});

var setDayColumnWidthArray = function() {
  dayColumnWidthArray = [];
  $("#calendar > #tabCalendar > thead > tr > .claHead").each(function() {
    dayColumnWidthArray.push($(this).width() + 1);
  });
};

var resizingCorrection = function() {
  $(".claEvent").each(
          function() {
            var left = $(this).css("left");
            var leftNum = parseInt(left.replace("px", ""))
                    - ($(".claTimeColumn").width() + 14);

            var dayWidth = getDayWidthAverage();

            var dayNumber = Math.floor(leftNum / dayWidth);
            $(this).css("width", dayColumnWidthArray[dayNumber] * 0.9);
            $(this).css("left", getLeftMargin(dayNumber));
          });
  setDayColumnWidthArray();
};

var getDayWidthAverage = function() {
  var sum = 0;
  for (var i = 0; i < dayColumnWidthArray.length; i++) {
    sum += dayColumnWidthArray[0];
  }
  return sum / dayColumnWidthArray.length;
};

/**
 * Baut die Tabelle und setzt die Termine eines Nutzers.
 * 
 * @param p_week
 * @param p_year
 */
var buildUserCalendar = function(p_week, p_year) {
  var week = checkWeek(p_week);
  $("#hidCurrentWeek").val(week);
  $("#hidCurrentYear").val(p_year);

  buildCalendar(week, p_year);
  setDayColumnWidthArray();
  showUserEvents(week, p_year);
};

/**
 * Überprüfe die angegebene Kalenderwoche.
 * 
 * @param p_week
 * @return Gibt eine Kalenderwoche zurück.
 */
var checkWeek = function(p_week) {
  return p_week === -1 ? new Date().getWeekNumber() : p_week;
};

/**
 * Hole die aktuelle Kalenderwoche.
 * 
 * @source http://stackoverflow.com/a/6117889
 * @return Gibt die aktuelle Kalenderwoche zurück.
 */
Date.prototype.getWeekNumber = function() {
  var date = new Date(+this);
  date.setHours(0, 0, 0);
  date.setDate(date.getDate() + 4 - (date.getDay() || 7));
  return Math
          .ceil((((date - new Date(date.getFullYear(), 0, 1)) / 8.64e7) + 1) / 7);
};

/**
 * Baue die Wochentabelle auf.
 * 
 * @param p_week
 * @param p_year
 * @return Gibt eine aufgebaute Tabelle zurück
 */
var buildCalendar = function(p_week, p_year) {
  var table = "<table id='tabCalendar'>";

  table += getTableHead(p_week, p_year);
  table += getTableBody();

  table += "</table>";

  $("#calendar").html(table);
};

/**
 * Baut den Kalenderkopf auf.
 * 
 * @param p_week
 * @param p_year
 * @return Gibt einen aufgebauten Kalenderkopf zurück.
 */
var getTableHead = function(p_week, p_year) {
  var firstDayOfWeek = getDateOfISOWeek(p_week, p_year);
  var tableHead = "<thead><tr>";

  for (var i = 0; i <= 7; i++) {
    if (i == 0) {
      tableHead += "<th id='tdTime'></th>";
    } else {
      tableHead += "<th id='head" + getDay(i) + "' class='claHead'>"
              + getDate(firstDayOfWeek, i - 1) + "</th>";
    }
  }

  tableHead += "</thead></tr>";
  return tableHead;

};

var getDay = function(p_dayNumber) {
  switch (p_dayNumber) {
  case 1:
    return "Monday";
  case 2:
    return "Tuesday";
  case 3:
    return "Wednesday";
  case 4:
    return "Thursday";
  case 5:
    return "Friday";
  case 6:
    return "Saturday";
  case 7:
    return "Sunday";
  }
}

/**
 * Hole den ersten Tag einer Kalenderwoche.
 * 
 * @source http://stackoverflow.com/a/16591175
 * @param p_week
 * @param p_year
 * @return Erster Tag der angegebenen Kalenderwoche.
 */
function getDateOfISOWeek(p_week, p_year) {
  var simple = new Date(p_year, 0, 1 + (p_week - 1) * 7);
  var dow = simple.getDay();
  var ISOweekStart = simple;
  if (dow <= 4) {
    ISOweekStart.setDate(simple.getDate() - simple.getDay() + 1);
  } else {
    ISOweekStart.setDate(simple.getDate() + 8 - simple.getDay());
  }
  return ISOweekStart;
}

/**
 * Hole ein formatiertes Datum.
 * 
 * @param p_firstDayOfWeek
 * @param p_additionalDays
 * @return Formatiertes Datum - "ddd. dd.MM.yyyy"
 */
var getDate = function(p_firstDayOfWeek, p_additionalDays) {
  var currentDate = new Date(p_firstDayOfWeek.getTime()
          + (p_additionalDays * 24 * 60 * 60 * 1000));

  var weekDay = currentDate.getDay();
  var weekDays = new Array("So.", "Mo.", "Di.", "Mi.", "Do.", "Fr.", "Sa.")

  var day = currentDate.getDate();
  if (day < 10) {
    day = "0" + day
  }

  var month = currentDate.getMonth() + 1;
  if (month < 10) {
    month = "0" + month
  }

  var year = currentDate.getFullYear();

  return weekDays[weekDay] + " " + day + "." + month + "." + year;
};

/**
 * Baue den Body der Tabelle.
 * 
 * @return Aufgabauter Body der Tabelle.
 */
var getTableBody = function() {
  var tableBody = "<tbody>";

  tableBody += getAllDayRow();
  tableBody += getTimeRows();

  tableBody += "</tbody>";
  return tableBody;
};

/**
 * Baue eine Tabellenreihe für ganztägige Termine.
 * 
 * @return Tabellenreihe für ganztägige Termine.
 */
var getAllDayRow = function() {
  var allDay = "<tr id='trAllDay'>";

  for (var i = 0; i <= 7; i++) {
    allDay += "<td class='claAllDay'></td>";
  }

  allDay += "</tr>";
  return allDay;
};

/**
 * Baue eine Tabellenreihe im halben stunden Zyklus.
 * 
 * @return Tabellenreihe im halben stunden Zyklus.
 */
var getTimeRows = function() {
  var timeRows = "";
  for (var i = 0; i < 24; i = i + 0.5) {
    timeRows += "<tr class='claTimeFrame'>";

    for (var j = 0; j <= 7; j++) {
      var columnClass = "";
      var columnValue = "";
      if (Number.isInteger(i)) {
        if (j === 0) {
          columnClass = "claFullHour claTimeColumn";
          columnValue = i;
        } else {
          columnClass = "claFullHour";
        }
      } else {
        columnClass = "claHalfHour";
      }
      timeRows += "<td class='" + columnClass + "'>" + columnValue + "</td>";
    }

    timeRows += "</tr>";
  }

  return timeRows;
};

/**
 * Hole alle Termine des Benutzers aus der Datenbank.
 * 
 * @return Termine des Benutzers.
 */
var showUserEvents = function(p_week) {
  $
          .ajax({
            type: "POST",
            url: "CalendarController",
            dataType: "json",
            data: {
              action: "getEvents",
              startTime: $("#headMonday").text().substring(5, 15),
              endTime: $("#headSunday").text().substring(5, 15),
              userid: $("#ckiUserId").val(),
              calendarId: $("input[name=Calendar]:checked").attr("id")
            },
            success: function(response) {
              var eventList = response;
              for (var i = 0; i < eventList.length; i++) {
                var event = eventList[i];
                var start = event.startTime;
                var end = event.endTime;
                var eventStart = new Date(start.year, start.month - 1,
                        start.dayOfMonth, start.hourOfDay, start.minute, 0);
                var eventEnd = new Date(end.year, end.month - 1,
                        end.dayOfMonth, end.hourOfDay, end.minute, 0);

                var dayNumber = eventStart.getDay() === 0 ? 6 : eventStart
                        .getDay() - 1;
                var eventMarginLeft = getLeftMargin(dayNumber);
                var eventMarginTop = getTopMargin(eventStart.getHours(),
                        eventStart.getMinutes());
                var eventHeight = getEventHeight(eventStart.getHours(),
                        eventStart.getMinutes(), eventEnd.getHours(), eventEnd
                                .getMinutes());

                $("#calendar").append(
                        "<a href='#' onClick='openEventWindow(" + event.id
                                + ")' id='" + event.id
                                + "' class='claEvent' style='width: "
                                + dayColumnWidthArray[dayNumber] * 0.9
                                + "px; height: " + eventHeight + "px;left: "
                                + eventMarginLeft + "px; top: "
                                + eventMarginTop + "px;'>" + event.title
                                + "</a>");

              }
              checkEventCollisions();
            },
            error: function(jqXHR, textStatus, errorThrown) {
              alert(errorThrown
                      + ": Der Kalendar konnte nicht angezeigt werden!");
            }
          });
};

var checkEventCollisions = function() {
  $(".claEvent").each(function() {
    var collisions = $(this).collision(".claEvent");
    if ((collisions.length - 1) != 0) {

    }
  });
};

var getLeftMargin = function(p_eventDayNumber) {
  var left = $(".claTimeColumn").width() + 14;

  left += getDayWidth(p_eventDayNumber);

  return left;
};

var getDayWidth = function(p_eventDayNumber) {
  var width = 0;
  for (var i = 0; i < p_eventDayNumber; i++) {
    width += dayColumnWidthArray[i];
  }
  return width;
};

var getTopMargin = function(p_eventStartHour, p_eventStartMinute) {
  var top = 77; // 10px padding-top + 20px <thead> + 45px .allDay + 2px
  // border
  var startMinutes = (p_eventStartHour * 60) + p_eventStartMinute;

  for (var i = 0; i < startMinutes; i += 30) {
    top += 14;
  }

  top += 14 * ((startMinutes % 30) / 30);

  return top;
};

var getEventHeight = function(p_eventStartHour, p_eventStartMinute,
        p_eventEndHour, p_eventEndMinute) {
  var height = 0;
  var totalMinutes = ((p_eventEndHour - p_eventStartHour) * 60)
          + (p_eventEndMinute - p_eventStartMinute);

  for (var i = 0; i < totalMinutes; i += 30) {
    height += 14;
  }

  height += 14 * ((totalMinutes % 30) / 30);

  return height;
};

/**
 * Gehe eine Woche zurück.
 */
var getLastWeek = function() {
  var currentWeek = parseInt($("#hidCurrentWeek").val());
  var currentYear = parseInt($("#hidCurrentYear").val());

  if (currentWeek <= 1) {
    currentYear--;
    currentWeek = 53;
  } else {
    currentWeek--;
  }
  buildUserCalendar(currentWeek, currentYear);
};

/**
 * Gehe zur nächsten Woche.
 */
var getNextWeek = function() {
  var currentWeek = parseInt($("#hidCurrentWeek").val());
  var currentYear = parseInt($("#hidCurrentYear").val());

  if (currentWeek >= 53) {
    currentYear++;
    currentWeek = 1;
  } else {
    currentWeek++;
  }

  buildUserCalendar(currentWeek, currentYear);
};

/**
 * Melde den Benutzer ab.
 */
var Logout = function() {
  $.ajax({
    type: "POST",
    url: "CalendarController",
    data: {
      action: "logout"
    }
  });
};

/**
 * PopUp Fenster für eine Termineingabe
 * 
 * @param p_eventId
 */
var openEventWindow = function(p_eventId) {
  window.open(
          "Event.jsp?calendar=" + $('input[name=Calendar]:checked').attr("id")
                  + "&user=" + $("#ckiUserId").val() + "&event=" + p_eventId,
          "", "width=775,height=525").focus();
};

var showCalendarCreation = function() {
  $("#txtNewCalendarName").val("");
  $("#divNewCalendar").css("display", "block");
}

var abortCalendarCreation = function() {
  $("#txtNewCalendarName").val("");
  $("#divNewCalendar").css("display", "none");
};

var saveNewCalendar = function() {
  $.ajax({
    type: "POST",
    url: "CalendarController",
    dataType: "json",
    data: {
      action: "CreateNewCalendar",
      userId: $("#ckiUserId").val(),
      calendarName: $("#txtNewCalendarName").val()
    },
    success: function(response) {
      $("#divCalendarSelection").append(
              "<div class='claCalendarSelection'><input type='radio' id='"
                      + response + "' name='Calendar' value='"
                      + $("#txtNewCalendarName").val() + "'><label for='"
                      + response + "'> " + $("#txtNewCalendarName").val()
                      + "</label></div>");
    },
    error: function(jqXHR, textStatus, errorThrown) {
      //alert(errorThrown + ": Es konnte kein neuer Kalender erstellt werden!");
    }
  });
  $("#divNewCalendar").css("display", "none");

};