
<!-- Destaques -->
<div id="itemImage" class="item-image"> 
    <div class="row">
       	<div id="produtoDestaque" class="col-xs-12 col-sm-4" onclick="window.location='view/detalhe_produto.php?idProduto=6';">
       		<div id="itemDestaqueToHover">
				<?php 
					$cod = 6;
					include('Includes\imagem.php'); 
				?>
	        	<div id="itemDestaque"  class="item-image">
					<?php echo $imagem; ?>
	        	</div>
	        	<div class="item-content">
	          		<div class="item-text product-price" >
		            	<div class="item-titulo" align="center"> <?php echo $nmProduto; ?>! </div> 
						
						<div id="preco">
							<span> Por: R$<?php echo $preco; ?></span>
							
							<div class="item-vezes"> <?php echo $valoresParcela; ?> </div>
						</div>
						
						<div class="item-inner-text"><?php echo $dsProduto; ?> </div>
	    	    	</div>
	        	</div>
        	</div>
      	</div>

      	<div id="produtoDestaque" class="col-xs-12 col-sm-4" onclick="window.location='view/detalhe_produto.php?idProduto=4';">
       		<div id="itemDestaqueToHover">
				<?php 
					$cod = 4;
					include('Includes\imagem.php'); 
				?>
	        	<div id="itemDestaque"  class="item-image">
					<?php echo $imagem; ?>
	        	</div>
	        	<div class="item-content">
	          		<div class="item-text product-price" >
		            	<div class="item-titulo" align="center"> <?php echo $nmProduto; ?>! </div> 
						
						<div id="preco">
							<span> Por: R$<?php echo $preco; ?></span>
							
							<div class="item-vezes"> <?php echo $valoresParcela; ?> </div>
						</div>
						
						<div class="item-inner-text"><?php echo $dsProduto; ?> </div>
	    	    	</div>
	        	</div>
        	</div>
      	</div>
		
       	<div id="produtoDestaque" class="col-xs-12 col-sm-4" onclick="window.location='view/detalhe_produto.php?idProduto=5';">
       		<div id="itemDestaqueToHover">
				<?php 
					$cod = 5;
					include('Includes\imagem.php'); 
				?>
	        	<div id="itemDestaque"  class="item-image">
					<?php echo $imagem; ?>
	        	</div>
	        	<div class="item-content">
	          		<div class="item-text product-price" >
		            	<div class="item-titulo" align="center"> <?php echo $nmProduto; ?>! </div> 
						
						<div id="preco">
							<span> Por: R$<?php echo $preco; ?></span>
							
							<div class="item-vezes"> <?php echo $valoresParcela; ?> </div>
						</div>
						
						<div class="item-inner-text"><?php echo $dsProduto; ?> </div>
	    	    	</div>
	        	</div>
        	</div>
      	</div>

<!-- Segunda linha de produtos destaque-->
<div id="itemImage" class="item-image"> 
    <div class="row">

      	<div  class="col-xs-12 col-sm-4">
	       		<div id="itemDestaqueToHover">
	        	<div id="itemDestaque" class="item-image">
	          		<img src="imagens\posterBatman.jpg" class="img-responsive">
	        	</div>
	        	<div class="item-content">
	          		<div class="item-text product-price" >
		            	<div class="item-titulo" align="center"> Pôster Batman VS Superman Day VS Night </div>
						
						<div id="preco">
							<span> Por: R$29,80</span>
							
							<div class="item-vezes"> Ou 2x Sem juros de R$14,90 </div>
						</div>
						
						<div class="item-inner-text">Lorem ipsum dolor sit amet, sed ludus quidam graeco cu, ne iusto populo integre duo, mutat autem deterruisset ei est. Nam enim doctus hendrerit ut. Vix ne posse facer. Mei salutatus disputando ei. Probo hendrerit an duo, pro eloquentiam comprehensam ut. Eam ea cetero est, ei eos quas insolens invenire. </div>
	    	    	</div>
	        	</div>
	        </div>
      	</div>

      	<div  class="col-xs-12 col-sm-4">
	       		<div id="itemDestaqueToHover">
	        	<div id="itemDestaque" class="item-image">
	          		<img src="imagens\posterBatman2.jpg" class="img-responsive">
	        	</div>
	        	<div class="item-content">
	          		<div class="item-text product-price" >
		            	<div class="item-titulo" align="center"> Pôster Batman VS Superman Hero Wanted </div>
						
						<div id="preco">
							<span> Por: R$29,80</span>
							
							<div class="item-vezes"> Ou 2x Sem juros de R$14,90 </div>
						</div>
						
						<div class="item-inner-text">Lorem ipsum dolor sit amet, sed ludus quidam graeco cu, ne iusto populo integre duo, mutat autem deterruisset ei est. Nam enim doctus hendrerit ut. Vix ne posse facer. Mei salutatus disputando ei. Probo hendrerit an duo, pro eloquentiam comprehensam ut. Eam ea cetero est, ei eos quas insolens invenire. </div>
	    	    	</div>
	        	</div>
	        </div>
      	</div>

      	<div  class="col-xs-12 col-sm-4">
	       		<div id="itemDestaqueToHover">
	        	<div id="itemDestaque" class="item-image">
	          		<img src="imagens\posterSuperman.jpg" class="img-responsive">
	        	</div>
	        	<div class="item-content">
	          		<div id="aumenta-desc" class="item-text product-price" >
		            	<div class="item-titulo" align="center"> Pôster Batman VS Superman Man VS God </div>
						
						<div id="preco">
							<span> Por: R$29,80</span>
							
							<div class="item-vezes"> Ou 2x Sem juros de R$14,90 </div>
						</div>
						
						<div class="item-inner-text">Lorem ipsum dolor sit amet, sed ludus quidam graeco cu, ne iusto populo integre duo, mutat autem deterruisset ei est. Nam enim doctus hendrerit ut. Vix ne posse facer. Mei salutatus disputando ei. Probo hendrerit an duo, pro eloquentiam comprehensam ut. Eam ea cetero est, ei eos quas insolens invenire. </div>
	    	    	</div>
	        	</div>
	        </div>
      	</div>

    </div>
</div>