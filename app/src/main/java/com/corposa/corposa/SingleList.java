package com.corposa.corposa;

/**
 * Created by Eric Teixeira on 15/09/2014.
 */
public class SingleList {

    public static final int MEDIDACERTA = 0;
    public static final int MODELAGEM = 1;
    public static final int CARBOX = 2;
    public static final int POSPARTO = 3;
    public static final int NOIVAS = 4;
    public static final int FACIAL5 = 5;
    public static final int TURBINADA = 6;
    public static final int MAISSA = 7;

    public String nome;
    public int id;
    private final int tipo;

    public SingleList(String nome, int tipo){
        this.nome = nome;
        this.tipo = tipo;


    }

    /** Retornaa Imagem do Smile.
     * As Imagems foram insediras no /res/drawable
     * @return
     */

    public int getImagem(){




        switch (tipo){
            case MEDIDACERTA:
                return R.drawable.imgmedida;
            case MODELAGEM :
                return R.drawable.imgmodelagem;
            case CARBOX:
                return R.drawable.imgcarbox;
            case POSPARTO:
                return R.drawable.imgposparto;
            case NOIVAS :
                return R.drawable.imgnoivas;
            case FACIAL5:
                return R.drawable.imgfacial;
            case TURBINADA:
                return R.drawable.imgturbinada;
            case MAISSA :
                return R.drawable.imgmais;

        }

        return R.drawable.abc_search_dropdown_light;
    }



}