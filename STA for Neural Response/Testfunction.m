clear all;
v1_white_noise_exp;
spike = find(spikeTrain == 1);
delay05 = delay(0.05,stim,spike);
delay10 = delay(0.10,stim,spike);
delay15 = delay(0.15,stim,spike);
delay20 = delay(0.20,stim,spike);
delay25 = delay(0.25,stim,spike);

imagesc(delay05);
colormap(gray(256));
axis equal;
figure();

imagesc(delay10);
colormap(gray(256));
axis equal;
figure();

imagesc(delay15);
colormap(gray(256));
axis equal;
figure();

imagesc(delay20);
colormap(gray(256));
axis equal;
figure();

imagesc(delay25);
colormap(gray(256));
axis equal;
