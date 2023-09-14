const meetingsJson = '[{"id":1,"title":"Quick connect","organised_by":"Gus Fring","date":"07-09-2023","start_time":"10:00","end_time":"11:00","type":"Conference call","members":10,"meeting_room":"Room A-17"},{"id":2,"title":"Java Training","organised_by":"Gus Fring","date":"09-09-2023","start_time":"13:00","end_time":"15:00","type":"Classroom Training","members":15,"meeting_room":"Room C-03"}]'
const managersJson = '[{"full_name":"Walter White","id":123,"email":"walter.white@gmail.com","phone":8638825635,"credits":2000,"role":"Manager","last_logged_in":"07-09-2023"},{"full_name":"Jesse Pinkman","id":195,"email":"jesse.pinkman@gmail.com","phone":8362553930,"credits":1000,"role":"Manager","last_logged_in":"05-09-2023"}]'
const meetings = JSON.parse(meetingsJson)

const meetingHeaders = ["ID", "Title", "Organised By", "Date", "Start Time", "End Time", "Type", "Members", "Meeting Room"];4

const managers = JSON.parse(managersJson)

function createTableFromObjects(data) {
    const table = document.createElement('table');
    table.className = "table"
    const tableHead = document.createElement('thead'); 
    const headerRow = document.createElement('tr');
    // Create table header row
    for (const key of meetingHeaders) {
      const headerCell = document.createElement('th');
      headerCell.scope = "col"
      headerCell.textContent = key;
      headerRow.appendChild(headerCell);
    }
    tableHead.appendChild(headerRow)
    table.appendChild(tableHead);
  
    const keys = Object.keys(data[0]);
    // Create table data rows
    for (const obj of data) {
      const dataRow = document.createElement('tr');
      for (const key of keys) {
        const dataCell = document.createElement('td');
        dataCell.classList.add('pt-3')
        dataCell.textContent = obj[key];
        dataRow.appendChild(dataCell);
      }
      table.appendChild(dataRow);
    }
  
    return table;
  }

  
  const table = createTableFromObjects(meetings);

  const tableContainer = document.getElementById('table-container');
  tableContainer.appendChild(table);

  document.getElementById("name").innerHTML = managers[0].full_name;
  document.getElementById("role").innerHTML = managers[0].role;
  document.getElementById("lastLoggedIn").innerHTML = "Last active : "+ managers[0].last_logged_in;

  document.getElementById("name-field").innerHTML = managers[0].full_name;
  document.getElementById("uniqueId-field").innerHTML = managers[0].id;
  document.getElementById("email-field").innerHTML = managers[0].email;
  document.getElementById("email-field").innerHTML = managers[0].email;
  document.getElementById("phone-field").innerHTML = managers[0].phone;
  document.getElementById("credits-field").innerHTML = managers[0].credits;
  document.getElementById("role-field").innerHTML = managers[0].role;
  document.getElementById("lastLoggedIn-field").innerHTML = managers[0].last_logged_in;

  console.log(JSON.stringify(meetings));
  console.log(JSON.stringify(managers));
  //update credits
  let currentDate = new Date();
  if(currentDate.getDay == 1 && currentDate.getHours == 6 && currentDate.getMinutes == 0 && currentDate.getSeconds == 0){
    for(const manager of managers){
        manager.credits = 2000;
    }
  }