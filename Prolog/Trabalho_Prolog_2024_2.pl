% Fatos
gosta(joão, ação).
gosta(joão, ficção).
gosta(maria, romance).
gosta(maria, comédia).
gosta(ana, ficção).
gosta(ana, drama).
gosta(carlos, ação).
gosta(carlos, comédia).
gosta(beatriz, romance).
gosta(beatriz, ficção).

filme(vingadores, ação).
filme(deadpool, comédia).
filme(titanic, romance).
filme(matrix, ficção).
filme(interestelar, ficção).
filme(o_diario_de_uma_paixão, romance).
filme(john_wick, ação).
filme(parasita, drama).
filme(guardioes_da_galáxia, ação).
filme(a_chegada, ficção).

% Regras
regra1(Usuario, Filme) :- filme(Filme,Genero)
,gosta(Usuario,Genero).

regra2(Filme) :- filme(Filme,Genero)
,gosta(Usuario1,Genero)
,gosta(Usuario2,Genero)
,Usuario1 \= Usuario2
,Usuario1 @< Usuario2.

regra3(Filme, Usuario1, Usuario2) :- filme(Filme,Genero)
,gosta(Usuario1,Genero)
,gosta(Usuario2,Genero)
,Usuario1 \= Usuario2
,Usuario1 @< Usuario2.



