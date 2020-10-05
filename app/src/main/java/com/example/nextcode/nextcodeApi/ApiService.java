package com.example.nextcode.nextcodeApi;

import com.example.nextcode.models.PlanesRespuesta;
import com.example.nextcode.models.PlanesUsuario;
import com.example.nextcode.models.PlanesUsuarioRespuesta;
import com.example.nextcode.models.Usuarios;
import com.example.nextcode.models.UsuariosRespuesta;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("usuarios")
    Call<UsuariosRespuesta> obtenerListaUsuarios();

    @GET("planes")
    Call<PlanesRespuesta> obtenerListaPlanes();

    @GET("usuarios?embed=planes?embed=plan")
    Call<UsuariosRespuesta> obtenerListaPlanesDeUsuarios();




    @POST("planesusuario")
    @FormUrlEncoded
    Call<PlanesUsuario> contratarPlan(
            @Field("usuario_id") int usuario_id,
            @Field("plan_id") int plan_id,
            @Field("status") String status
            );

    @POST("usuarios")
    @FormUrlEncoded
    Call<Usuarios> registrarUsuario(
                            @Field("cedula") String cedula,
                            @Field("contrasenia") String contrasenia,
                            @Field("nombres") String nombres,
                            @Field("apellidos") String apellidos,
                            @Field("correo") String correo,
                            @Field("status") String status
                            );


}
