package SpaceCraft.Controls;

import com.almasb.ents.AbstractControl;
import com.almasb.ents.Entity;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.ServiceType;
import com.almasb.fxgl.audio.AudioPlayer;
import com.almasb.fxgl.entity.component.PositionComponent;
import com.almasb.fxgl.time.LocalTimer;

import SpaceCraft.EntityCreator;
import javafx.util.Duration;

public class EnemyControl extends AbstractControl {
    private LocalTimer hTimer;
    private LocalTimer vTimer;
    private LocalTimer attackTimer;
    private Duration nextAttack = Duration.seconds(2);

    private boolean movingRight = true;

    private AudioPlayer audioPlayer;

    @Override
    public void onAdded(Entity entity) {
        audioPlayer = GameApplication.getService(ServiceType.AUDIO_PLAYER);

        hTimer = GameApplication.getService(ServiceType.LOCAL_TIMER);
        vTimer = GameApplication.getService(ServiceType.LOCAL_TIMER);
        attackTimer = GameApplication.getService(ServiceType.LOCAL_TIMER);
        hTimer.capture();
        vTimer.capture();
        attackTimer.capture();
    }

    @Override
    public void onUpdate(Entity entity, double tpf) {
        PositionComponent positionComponent = entity.getComponentUnsafe(PositionComponent.class);

        if (hTimer.elapsed(Duration.seconds(2))) {
            movingRight = !movingRight;
            hTimer.capture();
        }

        if (vTimer.elapsed(Duration.seconds(6))) {
            positionComponent.translateY(20);
            vTimer.capture();
        }

        if (attackTimer.elapsed(nextAttack)) {
            if (Math.random() < 0.3) {
                shoot();
            }
            nextAttack = Duration.seconds(5 * Math.random());
            attackTimer.capture();
        }

        positionComponent.translateX(movingRight ? 1 : -1);
    }

    private void shoot() {
        Entity bullet = EntityCreator.newBullet(getEntity());

        getEntity().getWorld().addEntity(bullet);

        audioPlayer.playSound("shoot" + (int)(Math.random() * 4 + 1) + ".wav");
    }
}

  


