
function f = delay(x, stim, spike)
    STA = zeros(11,11);
    for n = 1:length (spike)
        STA = STA + stim(:,:,spike(n)-x*60 );
    end
    f = STA ./ length(spike);
end
