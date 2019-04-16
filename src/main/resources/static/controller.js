
    //Controller Part 
    var app = angular.module("myApp", []);
    app.controller("myCtrl", function($scope, $http) {

      //HTTP Read- read employee by Id
      $scope.getEmployee = function(employee) {                            
        var tempid = $scope.employee.id;
        $http({
          method : 'GET',
          url : 'http://localhost:8080/employee/' + tempid
        }).then(function successCallback(response) {    
            $scope.employee.firstname = response.data.firstname;
            $scope.employee.lastname = response.data.lastname;
            $scope.employee.phone = response.data.phone;
            $scope.employee.email = response.data.email;
            $scope.employee.gender = response.data.gender;            
        }, function errorCallback(response) {
          $scope.employee = {
                    firstname : "",
                    lastname : "",                    
                    email : "",
                    phone : "",
                    gender : ""
                };
            alert(response.data.message);
            console.log(response.data);
        });        
        };

        //End READ
        $scope.cudEmployee = function(employee) { 
          var method = "";
          var tempid = "";   
          if($scope.command != 'Delete') {
            if ($scope.command == 'Create' ) {
              method = "POST";
              $scope.employee.id = null;
            } 
            else if ($scope.command == 'Update') {
              method = "PUT";
              tempid =  $scope.employee.id;
            }
            $http({
                method : method,
                url : 'http://localhost:8080/employee/' + tempid ,
                data : angular.toJson($scope.employee),   
                headers : {
                      'Content-Type' : 'application/json'
                }
            }).then( _success, _error );
          }
          else {
            var tempid = $scope.employee.id;            
            $http({
                method : 'DELETE',
                url : 'http://localhost:8080/employee/' + tempid 
            }).then( _success, _error );
          }

          function _success(response) {
            alert(response.data.message);
              $scope.employee = {
                        firstname : "",
                        lastname : "",                    
                        email : "",
                        phone : "",
                        gender : ""
                    };
            console.log(response.statusText);
          }
         
          function _error(response) {
            alert(response.data.message);
            console.log(response.statusText);
          }
        };
        //#endregion
    });    