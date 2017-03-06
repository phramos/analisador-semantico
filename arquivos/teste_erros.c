int main (int numero) {
    testerrohexadecimal()
    testerronumero()
    testerroidentificador()
    testerrocomentarioinline()
    testerrooperaddor()
}

/*Testes de erro ao se tentar usar um valor hexadecimal*/
void testerrohexadecimal(){
    int hexa1 = 0x23568;
    int hexa2 = 0xA3568;
    int hexa3 = 0xB35b68;
    int hexa4 = 0xABCDEFabcdef0123456789;
}

/*Testes de erro ao se tentar usar um valor errado para um número*/
void testerronumero(){
    float numeroerrado1 = 0.;
    float numeroerrado2 = -15.;
    float numeroerrado3 = +1.;
    float numeroerrado4 = +35.;

}

/*Testes de erro ao se tentar usar valores incorretos para identificadores*/
void testerroidentificador (){
    char 1erroident;
    char 2.0erroident;
    char -3E3erroident;
    char +4.4E4erroident;
}

/*Testes ao se tentar usar operadores errados*/
void testerrooperador {

    int operadorerrado1=0;
    int operadorerrado2=0;
    int operadorerrado3=0;

    operadorerrado1++;
    operadorerrado1--;
    operadorerrado1**;

}

/*Testes ao se tentar usar comentarios inline*/
void testerrocomentario{
    //sem comentários inline
    //isso sera tratado como um erro
    int errocoment; //aqui nao vai funcionar tambem
    if (errocomment){

    }//nao vai rolar fi, para de tentar
}