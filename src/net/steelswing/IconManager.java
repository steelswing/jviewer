/*
 * Ну вы же понимаете, что код здесь только мой?
 * Well, you do understand that the code here is only mine?
 */

package net.steelswing;

import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import net.steelswing.tree.FileNode;

/**
 * File: IconManager.java
 * Created on 24.08.2022, 12:03:18
 *
 * @author LWJGL2
 */
public class IconManager {

    public static final class ICONS {
        // FILE FORMATS 16x16 


        public static final ImageIcon ICON;
        public static final ImageIcon ICON16;
        public static final ImageIcon ICON32;
        public static final ImageIcon ICON48;

        public static final ImageIcon TRANSFORM_ICON16;
        public static final ImageIcon COG_ICON16;
        public static final ImageIcon TEXT_ICON16;
        public static final ImageIcon EDIT_ICON16;
        public static final ImageIcon EDIT_ICON32;

        public static final ImageIcon RUN_ICON16;
        public static final ImageIcon STOP_ICON16;

        public static final ImageIcon ICON_CLOSE_1_16;
        public static final ImageIcon ICON_CLOSE_2_16;

        public static final ImageIcon ICON_PACKAGE_16;
        public static final ImageIcon ICON_WOODEN_BOX_16;

        public static final ImageIcon ICON_MAGNIFIER_16;
        public static final ImageIcon ICON_MAGNIFIER_GO_16;

        public static final ImageIcon ICON_CUP_16;

        static {
            // FILE FORMATS 16x16 
            ICON = new ImageIcon(IconManager.class.getResource("/assets/used/icons/icon.png"));

            ICON16 = new ImageIcon(IconManager.class.getResource("/assets/used/icons/icon_16x16.png"));
            ICON32 = new ImageIcon(IconManager.class.getResource("/assets/used/icons/icon_32x32.png"));
            ICON48 = new ImageIcon(IconManager.class.getResource("/assets/used/icons/icon_48x48.png"));

            TRANSFORM_ICON16 = new ImageIcon(IconManager.class.getResource("/assets/used/3d.png"));
            TEXT_ICON16 = new ImageIcon(IconManager.class.getResource("/assets/used/style.png"));
            COG_ICON16 = new ImageIcon(IconManager.class.getResource("/assets/used/cog.png"));
            EDIT_ICON16 = new ImageIcon(IconManager.class.getResource("/assets/used/edit16.png"));
            EDIT_ICON32 = new ImageIcon(IconManager.class.getResource("/assets/used/edit.png"));

            RUN_ICON16 = new ImageIcon(IconManager.class.getResource("/assets/used/play.png"));
            STOP_ICON16 = new ImageIcon(IconManager.class.getResource("/assets/used/stop.png"));

            ICON_CLOSE_1_16 = new ImageIcon(IconManager.class.getResource("/assets/used/icon_close_1.png"));
            ICON_CLOSE_2_16 = new ImageIcon(IconManager.class.getResource("/assets/used/icon_close_2.png"));

            ICON_PACKAGE_16 = new ImageIcon(IconManager.class.getResource("/assets/used/old/package.png"));
            ICON_WOODEN_BOX_16 = new ImageIcon(IconManager.class.getResource("/assets/used/old/wooden-box.png"));

            ICON_MAGNIFIER_16 = new ImageIcon(IconManager.class.getResource("/assets/used/old/magnifier.png"));
            ICON_MAGNIFIER_GO_16 = new ImageIcon(IconManager.class.getResource("/assets/used/old/magnifier_go.png"));

            ICON_CUP_16 = new ImageIcon(IconManager.class.getResource("/assets/used/cup.png"));
        }
    }

    public static final class FORMATS {
        // FILE FORMATS 16x16 

        public static final ImageIcon PAGE_FBX_16ICON;
        public static final ImageIcon PAGE_GLSL_16ICON;
        public static final ImageIcon PAGE_JAVA_16ICON;
        public static final ImageIcon PAGE_JAVA_CLASS_16ICON;
        public static final ImageIcon PAGE_JAVA_CLASSES_16ICON;
        public static final ImageIcon PAGE_JSON_16ICON;
        public static final ImageIcon PAGE_OBJ_16ICON;
        public static final ImageIcon PAGE_PICTURE_16ICON;
        public static final ImageIcon PAGE_SCENE_16ICON;
        public static final ImageIcon PAGE_WHITE_16ICON;

        public static List<String> IMAGE_FORMATS = Arrays.asList("png", "jpg", "gif", "bmp", "wbmp");

        static {
            // FILE FORMATS 16x16 
            PAGE_FBX_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/page/page_fbx.png"));
            PAGE_GLSL_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/page/page_glsl.png"));
            PAGE_JAVA_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/page/page_java.png"));
            PAGE_JAVA_CLASS_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/page/page_java_class.png"));
            PAGE_JAVA_CLASSES_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/page/page_java_classes.png"));
            PAGE_JSON_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/page/page_json.png"));
            PAGE_OBJ_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/page/page_obj.png"));
            PAGE_PICTURE_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/page/page_picture.png"));
            PAGE_SCENE_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/page/page_scene.png"));
            PAGE_WHITE_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/page/page_white.png"));

        }

        public static boolean isImage(String extention) {
            if (extention != null) {
                extention = extention.toLowerCase();
                return IMAGE_FORMATS.contains(extention);
            }
            return false;
        }

        public static ImageIcon getByExtention(FileNode fileNode) {
            if (fileNode == null) {
                return PAGE_WHITE_16ICON;
            }
            if (fileNode.isRoot()) {
                return ICONS.ICON_PACKAGE_16;
            }
            if (fileNode.isDir()) {
                return FOLDER.FOLDER_CLOSE_16ICON;
            }

            return getByExtention(fileNode.getExtension());
        }

        public static ImageIcon getByExtention(String extention) {
            if (extention != null) {
                extention = extention.toLowerCase();
                if (IMAGE_FORMATS.contains(extention)) {
                    return IMAGES.IMAGE_16ICON;
                }
                if (extention.equalsIgnoreCase("jar") || extention.equalsIgnoreCase("zip")) {
                    return ICONS.ICON_WOODEN_BOX_16;
                }

                if (extention.equalsIgnoreCase("glsl") || extention.equalsIgnoreCase("fs") || extention.equalsIgnoreCase("vs") || extention.equalsIgnoreCase("vert") || extention.equalsIgnoreCase("frag")) {
                    return PAGE_GLSL_16ICON;
                }
                if (extention.equalsIgnoreCase("java")) {
                    return PAGE_JAVA_16ICON;
                }
                if (extention.equalsIgnoreCase("class")) {
                    return PAGE_JAVA_CLASS_16ICON;
                }
                if (extention.equalsIgnoreCase("json")) {
                    return PAGE_JSON_16ICON;
                }
                if (extention.equalsIgnoreCase("obj")) {
                    return PAGE_OBJ_16ICON;
                }
                if (extention.equalsIgnoreCase("fbx")) {
                    return PAGE_FBX_16ICON;
                }

                if (extention.equalsIgnoreCase("fbx")) {
                    return PAGE_FBX_16ICON;
                }
            }
            return PAGE_WHITE_16ICON;
        }
    }

    public static final class BRICK {
        // BRICKS 16x16

        public static final ImageIcon BRICK_16ICON;
        public static final ImageIcon BRICK_NEW_16ICON;
        public static final ImageIcon BRICK_DEL_16ICON;
        public static final ImageIcon BRICK_EDIT_16ICON;

        public static final ImageIcon BRICK_BLUE_16ICON;
        public static final ImageIcon BRICK_BLUE_NEW_16ICON;
        public static final ImageIcon BRICK_BLUE_DEL_16ICON;
        public static final ImageIcon BRICK_BLUE_EDIT_16ICON;

        // BRICKS 32x32
        public static final ImageIcon BRICK_32ICON;
        public static final ImageIcon BRICK_NEW_32ICON;
        public static final ImageIcon BRICK_DEL_32ICON;
        public static final ImageIcon BRICK_EDIT_32ICON;

        static {
            // BRICKS 16x16
            BRICK_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/brick/brick.png"));
            BRICK_NEW_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/brick/brick_new.png"));
            BRICK_DEL_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/brick/brick_delete.png"));
            BRICK_EDIT_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/brick/brick_edit.png"));

            BRICK_BLUE_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/brick/prefab.png"));
            BRICK_BLUE_NEW_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/brick/prefab_new.png"));
            BRICK_BLUE_DEL_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/brick/prefab_delete.png"));
            BRICK_BLUE_EDIT_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/brick/prefab_edit.png"));

            // BRICKS 32x32
            BRICK_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/brick/brick32.png"));
            BRICK_NEW_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/brick/brick32_new.png"));
            BRICK_DEL_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/brick/brick32_delete.png"));
            BRICK_EDIT_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/brick/brick32_edit.png"));
        }
    }

    public static final class BRICKS {
        // BRICKS 16x16

        public static final ImageIcon BRICKS_16ICON;
        public static final ImageIcon BRICKS_NEW_16ICON;
        public static final ImageIcon BRICKS_DEL_16ICON;
        public static final ImageIcon BRICKS_EDIT_16ICON;

        // BRICKS 32x32
        public static final ImageIcon BRICKS_32ICON;
        public static final ImageIcon BRICKS_NEW_32ICON;
        public static final ImageIcon BRICKS_DEL_32ICON;
        public static final ImageIcon BRICKS_EDIT_32ICON;

        static {
            // BRICKS 16x16
            BRICKS_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/bricks/bricks.png"));
            BRICKS_NEW_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/bricks/bricks_new.png"));
            BRICKS_DEL_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/bricks/bricks_delete.png"));
            BRICKS_EDIT_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/bricks/bricks_edit.png"));

            // BRICKS 32x32
            BRICKS_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/bricks/bricks32.png"));
            BRICKS_NEW_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/bricks/bricks32_new.png"));
            BRICKS_DEL_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/bricks/bricks32_delete.png"));
            BRICKS_EDIT_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/bricks/bricks32_edit.png"));
        }
    }

    public static final class LIGHT {
        // LIGHT 16x16

        public static final ImageIcon LIGHT_16ICON;
        public static final ImageIcon LIGHT_NEW_16ICON;
        public static final ImageIcon LIGHT_DEL_16ICON;

        public static final ImageIcon LIGHT_DIRECTION_16ICON;
        public static final ImageIcon LIGHT_DIRECTION_NEW_16ICON;
        public static final ImageIcon LIGHT_DIRECTION_DEL_16ICON;

        public static final ImageIcon LIGHT_PROJECTOR_16ICON;
        public static final ImageIcon LIGHT_PROJECTOR_NEW_16ICON;
        public static final ImageIcon LIGHT_PROJECTOR_DEL_16ICON;

        // LIGHT 32x32
        public static final ImageIcon LIGHT_32ICON;
        public static final ImageIcon LIGHT_NEW_32ICON;
        public static final ImageIcon LIGHT_DEL_32ICON;

        public static final ImageIcon LIGHT_DIRECTION_32ICON;
        public static final ImageIcon LIGHT_DIRECTION_NEW_32ICON;
        public static final ImageIcon LIGHT_DIRECTION_DEL_32ICON;

        public static final ImageIcon LIGHT_PROJECTOR_32ICON;
        public static final ImageIcon LIGHT_PROJECTOR_NEW_32ICON;
        public static final ImageIcon LIGHT_PROJECTOR_DEL_32ICON;

        static {
            // LIGHT 16x16
            LIGHT_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/light/light.png"));
            LIGHT_NEW_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/light/light_new.png"));
            LIGHT_DEL_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/light/light_delete.png"));

            // LIGHT 16x16
            LIGHT_DIRECTION_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/light/light_dir.png"));
            LIGHT_DIRECTION_NEW_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/light/light_dir_new.png"));
            LIGHT_DIRECTION_DEL_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/light/light_dir_delete.png"));

            // LIGHT PROJECTOR 16x16
            LIGHT_PROJECTOR_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/light/light_projector.png"));
            LIGHT_PROJECTOR_NEW_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/light/light_projector_new.png"));
            LIGHT_PROJECTOR_DEL_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/light/light_projector_delete.png"));

            // LIGHT 32x32
            LIGHT_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/light/light32.png"));
            LIGHT_NEW_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/light/light32_new.png"));
            LIGHT_DEL_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/light/light32_delete.png"));

            LIGHT_DIRECTION_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/light/light_dir.png"));
            LIGHT_DIRECTION_NEW_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/light/light_dir_new.png"));
            LIGHT_DIRECTION_DEL_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/light/light_dir_delete.png"));

            LIGHT_PROJECTOR_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/light/light_projector32.png"));
            LIGHT_PROJECTOR_NEW_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/light/light_projector32_new.png"));
            LIGHT_PROJECTOR_DEL_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/light/light_projector32_delete.png"));
        }
    }

    public static final class TOOL {

        // TOOL 16x16
        public static final ImageIcon NEW_16ICON;
        public static final ImageIcon REDO_16ICON;
        public static final ImageIcon UNDO_16ICON;
        public static final ImageIcon REFRESH_16ICON;
        public static final ImageIcon WARN_16ICON;

        public static final ImageIcon INFO_16ICON;
        public static final ImageIcon QUEST_16ICON;
        public static final ImageIcon SAVE_16ICON;
        public static final ImageIcon EXIT_16ICON;
        public static final ImageIcon CROSS_16ICON;

        public static final ImageIcon TREE_EXPAND_16ICON;
        public static final ImageIcon TREE_COLLAPSE_16ICON;

        // TOOL 32x32

        public static final ImageIcon NEW_32ICON;
        public static final ImageIcon WARN_32ICON;
        public static final ImageIcon QUEST_32ICON;
        public static final ImageIcon CROSS_32ICON;


        public static final ImageIcon TRANSLATE_32ICON;
        public static final ImageIcon SCALE_32ICON;
        public static final ImageIcon ROTATE_32ICON;

        public static final ImageIcon TRANSLATE_SELECTED_32ICON;
        public static final ImageIcon SCALE_SELECTED_32ICON;
        public static final ImageIcon ROTATE_SELECTED_32ICON;

        public static final ImageIcon RUN_ICON32;
        public static final ImageIcon STOP_ICON32;


        static {
            TREE_EXPAND_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/tree_down.png"));
            TREE_COLLAPSE_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/tree_up.png"));

            // LIGHT 16x16
            NEW_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/new.png"));
            REDO_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/redo.png"));
            UNDO_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/undo.png"));
            REFRESH_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/refresh.png"));
            WARN_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/warn.png"));
            SAVE_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/save.png"));
            EXIT_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/exit.png"));
            CROSS_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/cross.png"));

            INFO_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/info.png"));
            QUEST_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/quest.png"));

            // LIGHT 32x32
            NEW_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/new32.png"));
            WARN_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/warn32.png"));
            QUEST_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/quest32.png"));
            CROSS_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/cross32.png"));

            // gizmo
            TRANSLATE_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/button_translate.png"));
            SCALE_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/button_scale.png"));
            ROTATE_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/button_rotate.png"));

            TRANSLATE_SELECTED_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/button_translate_selected.png"));
            SCALE_SELECTED_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/button_scale_selected.png"));
            ROTATE_SELECTED_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/tool/button_rotate_selected.png"));

            RUN_ICON32 = new ImageIcon(IconManager.class.getResource("/assets/used/tool/play32.png"));
            STOP_ICON32 = new ImageIcon(IconManager.class.getResource("/assets/used/tool/button_Stop.png"));
        }
    }

    public static final class FOLDER {

        // FOLDER 16x16
        public static final ImageIcon FOLDER_CLOSE_16ICON;
        public static final ImageIcon FOLDER_NEW_16ICON;
        public static final ImageIcon FOLDER_OPEN_16ICON;

        public static final ImageIcon JAVA_PROJECT_16ICON;


        static {
            // FOLDER 16x16
            FOLDER_CLOSE_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/folder/folder_close.png"));
            FOLDER_NEW_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/folder/folder_new.png"));
            FOLDER_OPEN_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/folder/folder_open.png"));

            JAVA_PROJECT_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/folder/j2seProject.png"));
        }
    }

    public static final class DOCUMENT {

        // FOLDER 16x16
        public static final ImageIcon DOCUMENT_BLUEPRINT_64ICON;
        public static final ImageIcon DOCUMENT_EMITTER_64ICON;
        public static final ImageIcon DOCUMENT_IMAGE_64ICON;
        public static final ImageIcon DOCUMENT_SOUND_64ICON;

        public static final ImageIcon DOCUMENT_PNG_64ICON;
        public static final ImageIcon DOCUMENT_DDS_64ICON;
        public static final ImageIcon DOCUMENT_FBX_64ICON;
        public static final ImageIcon DOCUMENT_OBJ_64ICON;
        public static final ImageIcon DOCUMENT_UNKNOWN_64ICON;

        public static final ImageIcon DOCUMENT_FOLDER_CLOSE_64ICON;
        public static final ImageIcon DOCUMENT_FOLDER_OPEN_64ICON;

        public static final ImageIcon DOCUMENT_MATERIAL_64ICON;
        public static final ImageIcon DOCUMENT_SCENE_64ICON;

        static {
            DOCUMENT_BLUEPRINT_64ICON = new ImageIcon(IconManager.class.getResource("/assets/used/document/document_blueprint.png"));
            DOCUMENT_EMITTER_64ICON = new ImageIcon(IconManager.class.getResource("/assets/used/document/document_emitter.png"));
            DOCUMENT_IMAGE_64ICON = new ImageIcon(IconManager.class.getResource("/assets/used/document/document_img.png"));
            DOCUMENT_SOUND_64ICON = new ImageIcon(IconManager.class.getResource("/assets/used/document/document_sound.png"));

            DOCUMENT_PNG_64ICON = new ImageIcon(IconManager.class.getResource("/assets/used/document/document_png.png"));
            DOCUMENT_DDS_64ICON = new ImageIcon(IconManager.class.getResource("/assets/used/document/document_dds.png"));
            DOCUMENT_FBX_64ICON = new ImageIcon(IconManager.class.getResource("/assets/used/document/document_fbx.png"));
            DOCUMENT_OBJ_64ICON = new ImageIcon(IconManager.class.getResource("/assets/used/document/document_obj.png"));
            DOCUMENT_UNKNOWN_64ICON = new ImageIcon(IconManager.class.getResource("/assets/used/document/document_unknown.png"));

            DOCUMENT_FOLDER_CLOSE_64ICON = new ImageIcon(IconManager.class.getResource("/assets/used/document/folder_close.png"));
            DOCUMENT_FOLDER_OPEN_64ICON = new ImageIcon(IconManager.class.getResource("/assets/used/document/folder_open.png"));

            DOCUMENT_MATERIAL_64ICON = new ImageIcon(IconManager.class.getResource("/assets/used/document/document_mat.png"));
            DOCUMENT_SCENE_64ICON = new ImageIcon(IconManager.class.getResource("/assets/used/document/document_scene.png"));
        }
    }

    public static final class PROJECT {

        // FOLDER 16x16
        public static final ImageIcon PROJECT_NEW_16ICON;
        public static final ImageIcon PROJECT_LOAD_16ICON;

        static {
            PROJECT_NEW_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/project/application_new.png"));
            PROJECT_LOAD_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/project/application_get.png"));
        }
    }

    public static final class MATERIAL {

        public static final ImageIcon MATERIAL_16ICON;
        public static final ImageIcon MATERIAL_NEW_16ICON;

        public static final ImageIcon MATERIAL_32ICON;
        public static final ImageIcon MATERIAL_NEW_32ICON;
        public static final ImageIcon MATERIAL_DELETE_32ICON;

        static {
            MATERIAL_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/material/color_wheel.png"));
            MATERIAL_NEW_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/material/color_wheel_new.png"));

            MATERIAL_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/material/color_wheel32.png"));
            MATERIAL_NEW_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/material/color_wheel_new32.png"));
            MATERIAL_DELETE_32ICON = new ImageIcon(IconManager.class.getResource("/assets/used/material/color_wheel_delete32.png"));
        }
    }

    public static final class IMAGES {

        public static final ImageIcon IMAGE_16ICON;
        public static final ImageIcon IMAGE_LINK_16ICON;


        static {
            IMAGE_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/image/image.png"));
            IMAGE_LINK_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/image/image_link.png"));

        }
    }

    public static final class PAGE {

        public static final ImageIcon PAGE_SCRIPT_16ICON;


        static {
            PAGE_SCRIPT_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/page/script.png"));
        }
    }
    
    public static final class DECOMPILER {

        public static final ImageIcon APP_FERNFLOWER_16ICON;
        public static final ImageIcon APP_CFR_16ICON;
        public static final ImageIcon APP_PROCYON_16ICON;


        static {
            APP_FERNFLOWER_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/decompiler/fernflower.png"));
            APP_CFR_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/decompiler/cfr.png"));
            APP_PROCYON_16ICON = new ImageIcon(IconManager.class.getResource("/assets/used/decompiler/procyon.png"));
        }
    }
}