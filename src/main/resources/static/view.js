
      function openJob(evt, JobName) {
        // To switch between tabs
        var i, tabcontent, tablinks, error;
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
          tabcontent[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
          tablinks[i].className = tablinks[i].className.replace("active", "");
        }
        resetForm();

        // To show forms depending on the tabs selected
        if(JobName == "Create")
        {
          document.getElementById("taskheading").innerHTML = "Create new employee";
          document.getElementById("readID").style.display = "active";
          document.getElementById("empForm").style.display = "block";
        }
        else if (JobName == "Read") {
          document.getElementById("taskheading").innerHTML = "Read existing employee";
          document.getElementById("readID").style.display = "block";
          document.getElementById("empForm").style.display = "block";
        }
        else if (JobName == "Update") {
          document.getElementById("taskheading").innerHTML = "Update existing employee";
          document.getElementById("readID").style.display = "block";
          document.getElementById("empForm").style.display = "block";
        }
        else if (JobName == "Delete") {
          document.getElementById("taskheading").innerHTML = "Delete existing employee";
          document.getElementById("readID").style.display = "block";
          document.getElementById("empForm").style.display = "block";
        }

      }

    function resetForm() {
      // Clearing text box and radio buttons when switching between tabs
        document.getElementById("firstname").value = "";
        document.getElementById("lastname").value = "";
        document.getElementById("email").value = "";
        document.getElementById("phone").value = "";
        document.getElementById("id").value = "";
        document.getElementById("genderM").checked = false;
        document.getElementById("genderF").checked = false;

    }