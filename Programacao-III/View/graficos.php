<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <title>E-Commerce Maneiro</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  
   <!-- Bootstrap, JQuert, Javascript --> 
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-hQpvDQiCJaD2H465dQfA717v7lu5qHWtDbWNPvaTJ0ID5xnPUlVXnKzq7b8YUkbN" crossorigin="anonymous">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

  <!-- Fonts -->
  <link href='https://fonts.googleapis.com/css?family=Merriweather:400,700' rel='stylesheet' type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Josefin+Slab:700,600' rel='stylesheet' type='text/css'>

</head>

<body>

<div class="container"> 

<?php 
  include '../includes/navbar-logado.html';
  

  //conxão com o banco
  include("../Includes/conexao-banco.php");
    
  $query = "SELECT pes.nm_pessoa as Pessoa,
            sum(com.vl_total) as Valor
            from compras com, pessoa pes
            where com.idPessoa = pes.idPessoa
            group by com.idPessoa;";
    
  $result = mysqli_query($con, $query);
    
  $retorno = '';
    
  while($row = mysqli_fetch_array($result)) 
  {
    if ($retorno != '') 
    { 
      $retorno .= ','; 
    }
      
    $retorno .= '{"c":[{"v":"' . $row["Pessoa"] . '"},{"v":'. $row["Valor"] . '}]}';
  }
    
  $retorno = '{"cols": [{"label":"Pessoa","type":"string"},{"label":"Valor","type":"number"}],
               "rows":  [' . $retorno . ']}';


  //grafico 2
  $query = "SELECT pes.nm_pessoa as Pessoa,
                   count(*) as Compras
            from compras com, pessoa pes
            where com.idPessoa = pes.idPessoa
            group by com.idPessoa;";
    
  $result = mysqli_query($con, $query);
    
  $json2 = '';
    
  while($row = mysqli_fetch_array($result)) 
  {
    if ($json2 != '') 
    { 
      $json2 .= ','; 
    }
      
    $json2 .= '{"c":[{"v":"' . $row["Pessoa"] . '"},{"v":'. $row["Compras"] . '}]}';
  }
    
  $json2 = '{"cols": [{"label":"Pessoa","type":"string"},{"label":"Qtd_Compras","type":"number"}],
               "rows":  [' . $json2 . ']}';

  //grafico 3 
    $query = "SELECT (select count(*) from promocoes where status = 1) as Ativas,
                     (select count(*) from promocoes where status = 0) as Inativas 
                from promocoes
                group by Ativas, Inativas;";
    
  $result = mysqli_query($con, $query);
    
  $json3 = '';
    
  while($row = mysqli_fetch_array($result)) 
  {
    $ativas = $row['Ativas'];
    $inativas = $row['Inativas'];

    if ($json3 != '') 
    { 
      $json3 .= ','; 
    }
      
    $json3 .= '{"c":[{"v":"' . $row["Ativas"] . '"},{"v":'. $row["Inativas"] . '}]}';
  }
    
  $json3 = '{"cols": [{"label":"Ativas","type":"number"},{"label":"Inativas","type":"number"}],
               "rows":  [' . $json3 . ']}';
        

?>

  <!--Load the AJAX API-->
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">

    // Load the Visualization API and the corechart package.
    google.charts.load('current', {'packages':['corechart']});

    // Set a callback to run when the Google Visualization API is loaded.
    google.charts.setOnLoadCallback(drawChart);

    // Callback that creates and populates a data table, instantiates the pie chart, passes in the data and draws it.
    function drawChart() 
    {
      //Grafico 1 -Valor de Compra por Pessoa - Barras
      // Create the data table                        passa o json aqui
      var data = new google.visualization.DataTable(<?php echo $retorno ?>);

      // Set chart options
      var options = {'title':'Valor de Compra por Pessoa', 'width':400, 'height':300};

      // Instantiate and draw our chart, passing in some options.
      var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
      chart.draw(data, options);


      //Grafico 2 - Quantidade de Compras por Pessoa - Pizza
      var data2 = new google.visualization.DataTable(<?php echo $json2 ?>);

      // Set chart options
      var options = {'title':'Quantidade de Compras por Pessoa', 'width':400, 'height':300};

      // Instantiate and draw our chart, passing in some options.
      var chart2 = new google.visualization.PieChart(document.getElementById('chart2_div'));
      chart2.draw(data2, options);



      //Grafico 3
      var data3 = new google.visualization.DataTable();

      data3.addColumn('string', 'promocoes');
      data3.addColumn('number', 'Quantidade');

      var ativas = <?php echo $ativas ?>;
      var inativas = <?php echo $inativas ?>;

      data3.addRows([
        ['Ativas', ativas],
        ['Inativas', inativas]
        ]);

       var options = 
         {
           title: 'Promoções Ativas',
           width: 500,
           height: 300,
           legend: 'none',
           bar: {groupWidth: '95%'},
           vAxis: { gridlines: { count: 4 } }
         };


      var chart = new google.visualization.ColumnChart(document.getElementById('chart3_div'));
      chart.draw(data3, options);      
     
    }
  </script>

    <!--Div that will hold the pie chart-->
    <div class="row" style="padding-top: 50px;">
      <div class="col-xs-12 col-sm-4">
        <div id="chart_div" ></div>
      </div>
     
      <div class="col-xs-12 col-sm-4">
        <div id="chart2_div"></div>
      </div>
    
      <div class="col-xs-12 col-sm-4">
        <div id="chart3_div"></div>
      </div>
    </div>

  </div>


  </body>

  
</html>



