<%--
  Created by IntelliJ IDEA.
  User: jeanp
  Date: 23/11/2023
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="css/login_styles.css">
    <style>
        html,
        body,
        .intro {
            height: 100%;
        }

        table td,
        table th {
            text-overflow: ellipsis;
            white-space: nowrap;
            overflow: hidden;
        }

        .mask-custom {
            background: rgba(24, 24, 16, .2);
            border-radius: 2em;
            backdrop-filter: blur(25px);
            border: 2px solid rgba(255, 255, 255, 0.05);
            background-clip: padding-box;
            box-shadow: 10px 10px 10px rgba(46, 54, 68, 0.03);
        }
    </style>
</head>
<body>
<section class="intro">
    <div class="bg-image h-100" style="background-image: url('data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBw0NDw8NDQ0NDQ0NDw8NDQ0NDQ8NDQ0NFREWFhURFRUYHSggGBolGxUVITEhJSk3Ljo6Fx8zODMtNygtLjcBCgoKDQ0NDw8PFSsZFRkrKzctNysrKy03Ky0rNy0rNysrKy0tLSsrKysrKystKysrKysrKysrKysrKysrKysrK//AABEIAMkA+wMBIgACEQEDEQH/xAAYAAEBAQEBAAAAAAAAAAAAAAABAAIDB//EACMQAQEBAAEEAQQDAAAAAAAAAAARARICQcHwMSGRsdFRgaH/xAAXAQEBAQEAAAAAAAAAAAAAAAAAAQID/8QAFhEBAQEAAAAAAAAAAAAAAAAAABEB/9oADAMBAAIRAxEAPwDw5FLECKIBFEAilgEUQCKIBFEAiiARRAIogEUQCKIBFEAiiARSQCKIIxFoEUKARFAFDFBBFDDACMUABqKEKymoYQrKaihCshuCEKA3BCFZJigUCNRQGYmoIAiKFChQFQmDNEUMUWAijUUIlZTcUIViGNQxYlYijcUIVmKNxRYViKNxQhWIo3FCFYijcUIViKNxQhWII6QbhCsRRuCJFrMEbihCsRRuCJCsxQxQi1rMUazFG4zRmKNRQiVmGNRRYlEUazDCFYhjUMWJWIo6RZ0kKxxXF0izFiVjiuLedKhCsb0qOnE8VhXKKOkXEhXOCOvEbiQrnFHSCEK5wR03FEi1zijcUIVz3FG9wRItYg10giRaoUY0iwxQ5giizCcxYM5jUWZ5OYsTdUW4YYsZozFjWYosKNxRrcMIlZzDDmFYlZijUSwrHExrcEIVnMG43BuJFrMUaiiQrnuLca3PytSKxBG9/YhFrO4I1EkWsYI3GdZio5gII4GuzWJqhzEVxAcKzVQ4UVRYlTVQb5OZ9FvloQYjmnFKIocUEEUa/gAMZb7sihYdCKyNa0IrPb7hpnUXFgMCAz4Ea7BGmDnb3uy0yqaxhpU0tM1rGkWnAs+RGz0sVrFQ4Qq0h3y0zhohaximqHDrNO6qEVVkGr9Ro35CKdHdDUUbi3FRUVTyNw0bqKMCwVFSXv8AgRXM1nDjCtILN/CjTVYpq0aqwVZq1I3T06xWs3yqbjVOsU8lqRrC51qrUjVNYqpUjpVusU1aRpmjNVSjVFZqop3RRu+/cag0M/Y0YlWHRRRulWHBRRfazVh3VRo9+UqsZqrOaqzW43TWEVI2c1iqrUjos1inNWkbpzt/bnWs0qNZprFWatSN01zppSN1ZrFWatI6bprnVSpG81VzzTSkaqrFVKsavgbooqUjVGaKM1KsaorNVKNUM1UWFVmqpSOeaWU51tqqsmrRrEyqUbWazVmrUjdNYqq1I3VWKaUjVNYqpSN1VirNWkbprFVKkap3WKqUjVVYqpVjdFZqqUjVVZopSNVViqlWNVVmipSNUUUJVgxBVlqGmsoI1VWVQjVVZpokaqrNVUjVVZqpSN1ViqlI3VWaqVI1VWUUjVVZVKRqqsqlIaqKKVY1UzVQKFVQNVFFCNUUIWBBIpqoShpZQNJlA0magaQQFBAUEBQQFBAUEBQQFBAUADQFVA1BACEwFBAUEtCgihSxFEklEkgSSBJIEkgSSBJIEkgSQQKCKFBFH//Z');">
        <div class="mask d-flex align-items-center h-100">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12">
                        <div class="card mask-custom">
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-borderless text-white mb-0">
                                        <thead>
                                        <tr>
                                            <th scope="col">EMPLOYEES</th>
                                            <th scope="col">POSITION</th>
                                            <th scope="col">CONTACTS</th>
                                            <th scope="col">AGE</th>
                                            <th scope="col">ADDRESS</th>
                                            <th scope="col">SALARY</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <th scope="row">Tiger Nixon</th>
                                            <td>System Architect</td>
                                            <td>tnixon12@example.com</td>
                                            <td>61</td>
                                            <td>Edinburgh</td>
                                            <td>$320,800</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Sonya Frost</th>
                                            <td>Software Engineer</td>
                                            <td>sfrost34@example.com</td>
                                            <td>23</td>
                                            <td>Edinburgh</td>
                                            <td>$103,600</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Jena Gaines</th>
                                            <td>Office Manager</td>
                                            <td>jgaines75@example.com</td>
                                            <td>30</td>
                                            <td>London</td>
                                            <td>$90,560</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Quinn Flynn</th>
                                            <td>Support Lead</td>
                                            <td>qflyn09@example.com</td>
                                            <td>22</td>
                                            <td>Edinburgh</td>
                                            <td>$342,000</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Charde Marshall</th>
                                            <td>Regional Director</td>
                                            <td>cmarshall28@example.com</td>
                                            <td>36</td>
                                            <td>San Francisco</td>
                                            <td>$470,600</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Haley Kennedy</th>
                                            <td>Senior Marketing Designer</td>
                                            <td>hkennedy63@example.com</td>
                                            <td>43</td>
                                            <td>London</td>
                                            <td>$313,500</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Tatyana Fitzpatrick</th>
                                            <td>Regional Director</td>
                                            <td>tfitzpatrick00@example.com</td>
                                            <td>19</td>
                                            <td>Warsaw</td>
                                            <td>$385,750</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
