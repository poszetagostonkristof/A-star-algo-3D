function [] = aStarOpt

fileID = fopen('surface.txt','r');

fileID1 = fopen('kimenetBFS.txt','r');

fileID2 = fopen('kimenet.txt','r');

fileID3 = fopen('kimenetEnergiaval.txt','r');

C = textscan (fileID, '%f %f %f %d');
C1 = textscan (fileID1, '%f %f %f');
C2 = textscan (fileID2, '%f %f %f');
C3 = textscan (fileID3, '%f %f %f');

X = C{1}; Y = C{2}; Z = C{3};
x = zeros(250,1);
y = zeros(250,1);
z = zeros(250);

n = length(X);

%mivel a mesh alakja: mesh(tomb1(i), tomb2(j), matrix(i,j)), ezert
%elvegzem a szukseges skalazasokat
for i = 1:n
   x(X(i)-262) = X(i);
   y(Y(i)-131) = Y(i);
   z(X(i)-262,Y(i)-131) = Z(i);
end


X_ut1 = C1{1};
Y_ut1 = C1{2};
Z_ut1 = C1{3}; 

X_ut = C2{1};
Y_ut = C2{2};
Z_ut = C2{3}; 
 

X_ut2 = C3{1};
Y_ut2 = C3{2};
Z_ut2 = C3{3}; 

 mesh(y,x,z) %hoterkep kirajzolasa
 hold on
 
 plot3(Y_ut1,X_ut1,Z_ut1,'white.'); %bfs - legkisebb lepes
 hold on
 
 plot3(Y_ut,X_ut,Z_ut,'black.'); %a* - min tav
 hold on
  
 plot3(Y_ut2,X_ut2,Z_ut2,'red.'); %a* - energia
 view(160,0) %megfordítottam, hogy jobban látszódjon
 shading interp
 colorbar

fclose(fileID);
fclose(fileID2);
fclose(fileID1);
fclose(fileID3);
end

