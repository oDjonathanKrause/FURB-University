<?php

class Criptografia
{
    
    private $alfabeto_aux = 65;
    public  $alfabeto = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
    
    //Encriptação por Cifra de Cesar
    function encript_cesar($palavra, $chave)
    {
        //array de resultado
		$palavra_encriptada = "";
        
		//encripta
		for($i = 0; $i < strlen($palavra); $i++)
		{
			$catacter_encriptado = ord($palavra[$i]) + $chave;
			$palavra_encriptada .= chr($catacter_encriptado);    
		}
		
		return $palavra_encriptada;        
    }
    
    //Encriptação pela cifra de Vegenere
    function encript_vigenere($palavra, $chave)
    {
        //Corrige a chave
        $chave_aux = $chave;
        $contador = 0;
        
        while(strlen($palavra) > strlen($chave_aux))
        {
            $chave_aux .= $chave[$contador];
            $contador++;
            
            if($contador >= strlen($chave))
            {
                $contador = 0;
            }
        }

        //echo "Palavra de Entrada: " . $palavra;
        //echo "<br> Chave ajustada: " . $chave_aux;
        
        /*
        To encrypt a message: Ca = Ma + Kb (mod 26)
        To decrypt a message: Ma = Ca – Kb (mod 26)
        Where C = Code
        M = Message 
        K = Key 
        a = the ath character of the message bounded by the message, 
        b = the bth character of the Key bounded by the length of the key*/
        
        $palavra_encriptada = "";
        for ($i = 0; $i < strlen($palavra); $i++) 
        {
            $palavra_encriptada .= $this->alfabeto[(ord($palavra[$i]) - $this->alfabeto_aux + ord($chave_aux[$i]) - $this->alfabeto_aux)  % 26];
        }
        
        return $palavra_encriptada;
    }
    
    //Faz a transposicao da palavra
    function encript_transposicao($palavra)
    {
        //Declara matriz
        $matriz = array('linha_01' => array(),
                        'linha_02' => array());
        
        $palavra_original = $palavra;
        
        //Verifica se a palavra tem um numero par de letras
        if(strlen($palavra) % 2 != 0)
        {
            //Appenda mais uma letra para formar a matriz
            $palavra .= 'A';
            
            /*$resto = strlen($palavra) % 2;
            for($i = 0; $i <= $resto; $i++)
                $palavra .= 'A';*/
        }
        
        //Controi encriptação
        $i = 0;
        
        while($i < strlen($palavra))
        {
            //Appenda elemento na linha 1
            array_push($matriz['linha_01'], $palavra[$i]);
            
            //Appenda elemento na linah 2 
            array_push($matriz['linha_02'], $palavra[$i + 1]);
            $i += 2;
        }
        
        //Printa
        //Pra cada linha da matriz
        foreach($matriz as $linha => $elemento)
        {
            //Pra cada elemento da linha
            foreach($elemento as $el)
            {
                //Printa elemento
                echo $el;
            }
            
            //Nova linha
            echo '<br>';
        }
        
        return $matriz;
    }
    
    
    /* Desencriptação */
    //Desencripta Cifra de Cesar
    function desencript_cesar($palavra, $chave)
    {
		return $this->encript_cesar($palavra, -$chave);
    }
    
    //Desencripta a Transposicao 
    function desencript_transposicao($matriz)
    {
        $palavra_nova = "";
        
        //For pela matriz de palavras
        for($i = 0; $i < sizeof($matriz['linha_01']); $i++)
        {
            //Appenda a letra i da linha_01 + letra i da linha_02
            $palavra_nova .= $matriz['linha_01'][$i] . $matriz['linha_02'][$i];
        }
        
        return $palavra_nova;
    }
    
    //Desencripta Vigenere
    function desencript_vigenere($palavra, $chave)
    {
        //Corrige a chave
        $chave_aux = $chave;
        $contador = 0;
        
        while(strlen($palavra) > strlen($chave_aux))
        {
            $chave_aux .= $chave[$contador];
            $contador++;
            
            if($contador >= strlen($chave))
            {
                $contador = 0;
            }
        }

        //Desencripta
        $palavra_desencriptada = "";
        
        for ($i = 0; $i < strlen($palavra); $i++) 
        {
            $palavra_desencriptada .= $this->alfabeto[(ord($palavra[$i]) -ord($chave_aux[$i]) + 26) % 26];
        }

        return $palavra_desencriptada;
    }
    
    
   
    
}



?>