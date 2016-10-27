// this is the style you want to emulate.
// This is the canonical layout file for the Quantum project. If you want to add another keyboard,

#include "atreus.h"

//// Each layer gets a name for readability, which is then used in the keymap matrix below.
//// The underscores don't mean anything - you can have a layer called STUFF or any other name.
//// Layer names don't all need to be of the same length, obviously, and you can also skip them
//// entirely and just use numbers.
//#define _LW 2
//#define _QW 0
//#define _RS 1

const uint16_t PROGMEM keymaps[][MATRIX_ROWS][MATRIX_COLS] = {
[0] = [[==KEY_MAP_0==]],

const uint16_t PROGMEM fn_actions[] = {

};

const macro_t *action_get_macro(keyrecord_t *record, uint8_t id, uint8_t opt)
{
  // MACRODOWN only works in this function
      switch(id) {
        case 0:
          if (record->event.pressed) {
            register_code(KC_RSFT);
          } else {
            unregister_code(KC_RSFT);
          }
        break;
      }
    return MACRO_NONE;
};
