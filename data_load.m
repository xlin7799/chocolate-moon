function [train_imgs, train_label_use, test_imgs, test_label_use, train_label_compare, test_label_compare] ...
    = data_load(train_num, test_num)
whole_num = train_num + test_num;
[imgs,labels] = readMNIST('train-images.idx3-ubyte', 'train-labels.idx1-ubyte', whole_num, 0);
label = zeros(whole_num,10);
for i = 1 : whole_num
   switch labels(i,1)
       case 0
           label(i,:) = [0 0 0 0 0 0 0 0 0 1];
       case 1
           label(i,:) = [0 0 0 0 0 0 0 0 1 0];
       case 2
           label(i,:) = [0 0 0 0 0 0 0 1 0 0];
       case 3
           label(i,:) = [0 0 0 0 0 0 1 0 0 0];
       case 4
           label(i,:) = [0 0 0 0 0 1 0 0 0 0];
       case 5
           label(i,:) = [0 0 0 0 1 0 0 0 0 0];
       case 6
           label(i,:) = [0 0 0 1 0 0 0 0 0 0];
       case 7
           label(i,:) = [0 0 1 0 0 0 0 0 0 0];
       case 8
           label(i,:) = [0 1 0 0 0 0 0 0 0 0];
       case 9
           label(i,:) = [1 0 0 0 0 0 0 0 0 0];
   end
end
r = rand(1,whole_num);
[~, n] = sort(r);
train_imgs = imgs(:,:,n(1:train_num));
train_label_use = label(n(1:train_num),:);
test_imgs = imgs(:,:,n(train_num + 1:whole_num));
test_label_use = label(n(train_num + 1:whole_num),:);
train_label_compare = labels(n(1:train_num),:);
test_label_compare = labels(n(train_num + 1:whole_num),:);
end